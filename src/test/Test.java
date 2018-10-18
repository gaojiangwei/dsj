package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test {

	public static void main(String[] args) {
		/*
		 * 利润总额=营业利润+营业外收支净额 营业利润=营业收入净额-营业总成本+公允价值变动收益+投资收益+其他收益
		 * 营业收入净额=主营业务收入+其他业务收入 主营业务收入=售电收入+输电收入+委托运行维护收入+可再生能源补贴收入 售电收入=售电量*售电价
		 * 营业总成本=购电成本+输配电成本+其他业务成本+财务费用+税金及附加+资产减值损失 营业外收支净额=+
		 */

		// 公式
		/*String[][] exp = { { "利润总额", "=", "营业利润", "+", "营业外收支净额" },
				{ "营业利润", "=", "营业收入净额", "-", "营业总成本", "+", "公允价值变动收益", "+", "投资收益", "+", "其他收益" },
				{ "营业收入净额", "=", "主营业务收入", "+", "其他业务收入" },
				{ "主营业务收入", "=", "售电收入", "+", "输电收入", "+", "委托运行维护收入", "+", "可再生能源补贴收入", "+", "其他收入" },
				{ "其他收入", "=", "收入1", "-", "收入2", "*", "(", "收入3", "+", "收入4", ")" } };*/
		String[][] exp = {{ "利润总额", "=", "营业利润", "+", "营业外收支净额" },
				{ "营业利润", "=", "营业收入净额", "-", "营业总成本", "+", "公允价值变动收益", "+", "投资收益", "+", "其他收益" },
				{ "营业收入净额", "=", "主营业务收入", "+", "其他业务收入" },
				{"营业总成本", "=", "购电成本", "+", "输配电成本", "+", "其他业务成本", "+", "财务费用", "+", "税金及附加", "+", "资产减值损失"},
				{ "主营业务收入", "=", "售电收入", "*", "10", "+", "输电收入", "+", "委托运行维护收入", "+", "可再生能源补贴收入"},
				{ "售电收入", "=", "售电量", "*", "售电价"},
				{"营业外收支净额", "=", "营业外收入", "-", "营业外支出"}};
		compute3(exp);
	}

	public static void compute3(String exp[][]) {
		
		double m = 0;
		double n = 0;
		
		//集合实现
		ArrayList<String> list = null;  //保存每个公式
		ArrayList<List<String>> list2 = new ArrayList<List<String>>();//保存list
		for(int i = 0; i < exp.length; i++) {
			list = new ArrayList<String>();
			for(int j = 0; j < exp[i].length; j++) {
				list.add(exp[i][j]);
			}
			list2.add(list);
		}
		for(int i = list2.size() - 1; i >= 0; i--) {
			String aa[] = new String[list2.get(i).size()-2];//将每个公式=后面的存入数组
			for(int j = 2; j < list2.get(i).size(); j++) {
				aa[j-2] = list2.get(i).get(j);
			}
			
			for(int k = 0; k < list2.size(); k++) {//循环每个公式
				int t = 0;
				for(int j = 2; j < list2.get(k).size(); j++) {//循环每个公式=后面的每个因子
					if(list2.get(k).get(j).equals(list2.get(i).get(0))) {
						list2.get(k).remove(j);
						list2.get(k).add(j, "(");
						t = j+1;
						for(int r = 0; r < aa.length; r++) {
							list2.get(k).add(t++, aa[r]);
						}
						list2.get(k).add(t, ")");
					}
				}
			}
		}

		//数组实现
		/*
		    //整合公式
			String s = "";
			for (int i = exp.length - 1; i >= 0; i--) {
			s = "";
			for (int j = 2; j < exp[i].length; j++) {//每个公式都从=后面开始整合
				s += exp[i][j];
			}

			for(int k = 0; k < exp.length; k++) {
				for (int j = 2; i > 0 && j < exp[k].length; j++) {
					if (exp[k][j].equals(exp[i][0])) {
						exp[k][j] = "(" + s + ")";
					}
				}
			}
		}*/
		//整合之后的公式
		String str = "";
		for(int j = 0; j < list2.get(0).size(); j++) {
			str += list2.get(0).get(j);
		}
		/*for (int j = 0; j < exp[0].length; j++) {
			str += exp[0][j];
		}*/
		System.out.println(str);

		//将公式中的字符串替换为具体的值
		String title[] = str.split("[+ \\- * / ( ) =]");
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> name0 = new ArrayList<String>();
		ArrayList<String> name1 = new ArrayList<String>();
		//匹配公式中的常量
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
          
		for (int i = 1; i < title.length; i++) {
			if (title[i] != null && !title[i].equals("") && !pattern.matcher(title[i]).matches()) {
				name.add(title[i]);
				name0.add(title[i]);
				name1.add(title[i]);
			}
		}

		String sql = "select ";
		for(int i = 0; i < name.size(); i++) {
			sql += name.get(i) + ",";
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " from table";
		System.out.println(sql);
		//@Autowired
		/*private JdbcTemplate jdbc;
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("datas", list);*/
		/*double val[] = {1, 3, 6, 1, 2, 5, 2, 3, 4, 3, 8, 6, 3, 4, 1, 7, 9};//第一组值
		double val2[] = {6, 0, 4, 1, 3, 6, 5, 2, 1, 8, 2, 8, 1, 6, 4, 6, 7};//第二组值
		*/
		double val[] = {202098434.34, 530.85, 430192708.51, 193051027.69,22083767186.32, 370734694.58, 4069481181.55, 684665026407.09,
				 354425970218.98, 2902192144.37, 15897586091.66, 21228044634.42,642167960.63, 681062.2, 7916908559.76, 1007092786.56, 96979170.49};
		double val2[] = {181892433.28, 529.72, 462184204.05, 45483533.79,24939914283.79, 262003305.67, 3860645079.33, 606551275293.09,
				 324654640183.75, 2503445223.08, 17212087265.81, 20162342337.9,-470714985.59, -22521451.03, 7447563084.18, 741619009.22,100357700.83};
		int t = 0;
		String ss = "";
		//第0个n的值
		for(int i = 0; i < val.length; i++) {
			name0.set(i, String.valueOf(val[i]));
		}
		ss = str;
		for (int i = 0; i < val.length; i++) {
			if (str.indexOf(name1.get(i)) != 0) {
				if(Double.parseDouble(name0.get(i)) < 0) {
					name0.set(i, "(" + name0.get(i) + ")");
				}
				ss = ss.replace(name1.get(i), name0.get(i));
			}
		}
		String res0[] = ss.split("=");
		//计算字符串数值表达式
		ScriptEngineManager manager = new ScriptEngineManager();
		//通过调用 JavaScript 来计算
		ScriptEngine engine = manager.getEngineByName("js"); 

		try {
			n = (double) engine.eval(res0[1]);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		//从第0个n的值后开始循环替代
		for (int k = 0; k < val.length; k++) {
			for(int j = 0; j <= k; j++) {
				name.set(j, String.valueOf(val2[j]));
				t = j;
			}
			
			for(t++; t < val2.length; t++) {
				name.set(t, String.valueOf(val[t]));
			}
			ss = str;
			for (int i = 0; i < val.length; i++) {
				if (str.indexOf(name1.get(i)) != 0) {
					if(Double.parseDouble(name.get(i)) < 0) {
						name.set(i, "(" + name.get(i) + ")");
					}
					ss = ss.replace(name1.get(i), name.get(i));
				}
			}
			//System.out.println("&&&--->"+ss);
			String res[] = ss.split("=");

			//将字符串公式转换为实际公式进行计算
			manager = new ScriptEngineManager();
			engine = manager.getEngineByName("js"); 

			try {
				m = (double) engine.eval(res[1]);
				//new BigDecimal((m-n)).toPlainString()   将科学计数法转换为普通数字
				System.out.println("单因素变动影响(m-n)=" + new BigDecimal((m-n)).toPlainString() + "------m=" + m +"  n=" + n +"------影响因子=" + name1.get(k));
				n = m;
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
	}
}