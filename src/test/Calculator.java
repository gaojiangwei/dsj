package test;
import java.util.Collections;
import java.util.Stack;

/**
 *  绠楁暟琛ㄨ揪寮忔眰鍊� 
 *  鐩存帴璋冪敤Calculator鐨勭被鏂规硶conversion() 
 *  浼犲叆绠楁暟琛ㄨ揪寮忥紝灏嗚繑鍥炰竴涓诞鐐瑰�肩粨鏋�
 *  濡傛灉璁＄畻杩囩▼閿欒锛屽皢杩斿洖涓�涓狽aN
 */
public class Calculator {
    private Stack<String> postfixStack = new Stack<String>();// 鍚庣紑寮忔爤
    private Stack<Character> opStack = new Stack<Character>();// 杩愮畻绗︽爤
    private int[] operatPriority = new int[] { 0, 3, 2, 1, -1, 1, 0, 2 };// 杩愮敤杩愮畻绗SCII鐮�-40鍋氱储寮曠殑杩愮畻绗︿紭鍏堢骇

    public static double conversion(String expression) {
        double result = 0;
        Calculator cal = new Calculator();
        try {
            expression = transform(expression);
            result = cal.calculate(expression);
        } catch (Exception e) {
            // e.printStackTrace();
            // 杩愮畻閿欒杩斿洖NaN
            return 0.0 / 0.0;
        }
        // return new String().valueOf(result);
        return result;
    }

    /**
     * 灏嗚〃杈惧紡涓礋鏁扮殑绗﹀彿鏇存敼
     *
     * @param expression
     *            渚嬪-2+-1*(-3E-2)-(-1) 琚浆涓� ~2+~1*(~3E~2)-(~1)
     * @return
     */
    private static String transform(String expression) {
        char[] arr = expression.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if (i == 0) {
                    arr[i] = '~';
                } else {
                    char c = arr[i - 1];
                    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == 'E' || c == 'e') {
                        arr[i] = '~';
                    }
                }
            }
        }
        if(arr[0]=='~'||arr[1]=='('){
            arr[0]='-';
            return "0"+new String(arr);
        }else{
            return new String(arr);
        }
    }

    /**
     * 鎸夌収缁欏畾鐨勮〃杈惧紡璁＄畻
     *
     * @param expression
     *            瑕佽绠楃殑琛ㄨ揪寮忎緥濡�:5+12*(3+5)/7
     * @return
     */
    public double calculate(String expression) {
        Stack<String> resultStack = new Stack<String>();
        prepare(expression);
        Collections.reverse(postfixStack);// 灏嗗悗缂�寮忔爤鍙嶈浆
        String firstValue, secondValue, currentValue;// 鍙備笌璁＄畻鐨勭涓�涓�硷紝绗簩涓�煎拰绠楁湳杩愮畻绗�
        while (!postfixStack.isEmpty()) {
            currentValue = postfixStack.pop();
            if (!isOperator(currentValue.charAt(0))) {// 濡傛灉涓嶆槸杩愮畻绗﹀垯瀛樺叆鎿嶄綔鏁版爤涓�
                currentValue = currentValue.replace("~", "-");
                resultStack.push(currentValue);
            } else {// 濡傛灉鏄繍绠楃鍒欎粠鎿嶄綔鏁版爤涓彇涓や釜鍊煎拰璇ユ暟鍊间竴璧峰弬涓庤繍绠�
                secondValue = resultStack.pop();
                firstValue = resultStack.pop();

                // 灏嗚礋鏁版爣璁扮鏀逛负璐熷彿
                firstValue = firstValue.replace("~", "-");
                secondValue = secondValue.replace("~", "-");

                String tempResult = calculate(firstValue, secondValue, currentValue.charAt(0));
                resultStack.push(tempResult);
            }
        }
            return Double.valueOf(resultStack.pop());
    }

    /**
     * 鏁版嵁鍑嗗闃舵灏嗚〃杈惧紡杞崲鎴愪负鍚庣紑寮忔爤
     * 
     * @param expression
     */
    private void prepare(String expression) {
        opStack.push(',');// 杩愮畻绗︽斁鍏ユ爤搴曞厓绱犻�楀彿锛屾绗﹀彿浼樺厛绾ф渶浣�
        char[] arr = expression.toCharArray();
        int currentIndex = 0;// 褰撳墠瀛楃鐨勪綅缃�
        int count = 0;// 涓婃绠楁湳杩愮畻绗﹀埌鏈绠楁湳杩愮畻绗︾殑瀛楃鐨勯暱搴︿究浜庢垨鑰呬箣闂寸殑鏁板��
        char currentOp, peekOp;// 褰撳墠鎿嶄綔绗﹀拰鏍堥《鎿嶄綔绗�
        for (int i = 0; i < arr.length; i++) {
            currentOp = arr[i];
            if (isOperator(currentOp)) {// 濡傛灉褰撳墠瀛楃鏄繍绠楃
                if (count > 0) {
                    postfixStack.push(new String(arr, currentIndex, count));// 鍙栦袱涓繍绠楃涔嬮棿鐨勬暟瀛�
                }
                peekOp = opStack.peek();
                if (currentOp == ')') {// 閬囧埌鍙嶆嫭鍙峰垯灏嗚繍绠楃鏍堜腑鐨勫厓绱犵Щ闄ゅ埌鍚庣紑寮忔爤涓洿鍒伴亣鍒板乏鎷彿
                    while (opStack.peek() != '(') {
                        postfixStack.push(String.valueOf(opStack.pop()));
                    }
                    opStack.pop();
                } else {
                    while (currentOp != '(' && peekOp != ',' && compare(currentOp, peekOp)) {
                        postfixStack.push(String.valueOf(opStack.pop()));
                        peekOp = opStack.peek();
                    }
                    opStack.push(currentOp);
                }
                count = 0;
                currentIndex = i + 1;
            } else {
                count++;
            }
        }
        if (count > 1 || (count == 1 && !isOperator(arr[currentIndex]))) {// 鏈�鍚庝竴涓瓧绗︿笉鏄嫭鍙锋垨鑰呭叾浠栬繍绠楃鐨勫垯鍔犲叆鍚庣紑寮忔爤涓�
            postfixStack.push(new String(arr, currentIndex, count));
        }

        while (opStack.peek() != ',') {
            postfixStack.push(String.valueOf(opStack.pop()));// 灏嗘搷浣滅鏍堜腑鐨勫墿浣欑殑鍏冪礌娣诲姞鍒板悗缂�寮忔爤涓�
        }
    }

    /**
     * 鍒ゆ柇鏄惁涓虹畻鏈鍙�
     *
     * @param c
     * @return
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    /**
     * 鍒╃敤ASCII鐮�-40鍋氫笅鏍囧幓绠楁湳绗﹀彿浼樺厛绾�
     *
     * @param cur
     * @param peek
     * @return
     */
    public boolean compare(char cur, char peek) {// 濡傛灉鏄痯eek浼樺厛绾ч珮浜巆ur锛岃繑鍥瀟rue锛岄粯璁ら兘鏄痯eek浼樺厛绾ц浣�
        boolean result = false;
        if (operatPriority[(peek) - 40] >= operatPriority[(cur) - 40]) {
            result = true;
        }
        return result;
    }

    /**
     * 鎸夌収缁欏畾鐨勭畻鏈繍绠楃鍋氳绠�
     *
     * @param firstValue
     * @param secondValue
     * @param currentOp
     * @return
     */
    private String calculate(String firstValue, String secondValue, char currentOp) {
        String result = "";
        switch (currentOp) {
        case '+':
            result = String.valueOf(ArithHelper.add(firstValue, secondValue));
            break;
        case '-':
            result = String.valueOf(ArithHelper.sub(firstValue, secondValue));
            break;
        case '*':
            result = String.valueOf(ArithHelper.mul(firstValue, secondValue));
            break;
        case '/':
            result = String.valueOf(ArithHelper.div(firstValue, secondValue));
            break;
        }
        return result;
    }
}