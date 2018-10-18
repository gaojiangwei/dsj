package test;
public class ArithHelper {

    // 榛樿闄ゆ硶杩愮畻绮惧害
    private static final int DEF_DIV_SCALE = 16;

    // 杩欎釜绫讳笉鑳藉疄渚嬪寲
    private ArithHelper() {
    }

    /**
     * 鎻愪緵绮剧‘鐨勫姞娉曡繍绠椼��
     *
     * @param v1 琚姞鏁�
     * @param v2 鍔犳暟
     * @return 涓や釜鍙傛暟鐨勫拰
     */

    public static double add(double v1, double v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(Double.toString(v1));
        java.math.BigDecimal b2 = new java.math.BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double add(String v1, String v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
        java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 鎻愪緵绮剧‘鐨勫噺娉曡繍绠椼��
     *
     * @param v1 琚噺鏁�
     * @param v2 鍑忔暟
     * @return 涓や釜鍙傛暟鐨勫樊
     */

    public static double sub(double v1, double v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(Double.toString(v1));
        java.math.BigDecimal b2 = new java.math.BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double sub(String v1, String v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
        java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 鎻愪緵绮剧‘鐨勪箻娉曡繍绠椼��
     *
     * @param v1
     *            琚箻鏁�
     * @param v2
     *            涔樻暟
     * @return 涓や釜鍙傛暟鐨勭Н
     */

    public static double mul(double v1, double v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(Double.toString(v1));
        java.math.BigDecimal b2 = new java.math.BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double mul(String v1, String v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
        java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 鎻愪緵锛堢浉瀵癸級绮剧‘鐨勯櫎娉曡繍绠楋紝褰撳彂鐢熼櫎涓嶅敖鐨勬儏鍐垫椂锛岀簿纭埌 灏忔暟鐐逛互鍚�10浣嶏紝浠ュ悗鐨勬暟瀛楀洓鑸嶄簲鍏ャ��
     *
     * @param v1
     *            琚櫎鏁�
     * @param v2
     *            闄ゆ暟
     * @return 涓や釜鍙傛暟鐨勫晢
     */

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static double div(String v1, String v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
        java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
        return b1.divide(b2, DEF_DIV_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 鎻愪緵锛堢浉瀵癸級绮剧‘鐨勯櫎娉曡繍绠椼�傚綋鍙戠敓闄や笉灏界殑鎯呭喌鏃讹紝鐢眘cale鍙傛暟鎸� 瀹氱簿搴︼紝浠ュ悗鐨勬暟瀛楀洓鑸嶄簲鍏ャ��
     *
     * @param v1 琚櫎鏁�
     * @param v2 闄ゆ暟
     * @param scale 琛ㄧず琛ㄧず闇�瑕佺簿纭埌灏忔暟鐐逛互鍚庡嚑浣嶃��
     * @return 涓や釜鍙傛暟鐨勫晢
     */

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        java.math.BigDecimal b1 = new java.math.BigDecimal(Double.toString(v1));
        java.math.BigDecimal b2 = new java.math.BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 鎻愪緵绮剧‘鐨勫皬鏁颁綅鍥涜垗浜斿叆澶勭悊銆�
     *
     * @param v 闇�瑕佸洓鑸嶄簲鍏ョ殑鏁板瓧
     * @param scale 灏忔暟鐐瑰悗淇濈暀鍑犱綅
     * @return 鍥涜垗浜斿叆鍚庣殑缁撴灉
     */

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        java.math.BigDecimal b = new java.math.BigDecimal(Double.toString(v));
        java.math.BigDecimal one = new java.math.BigDecimal("1");
        return b.divide(one, scale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        java.math.BigDecimal b = new java.math.BigDecimal(v);
        java.math.BigDecimal one = new java.math.BigDecimal("1");
        return b.divide(one, scale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}