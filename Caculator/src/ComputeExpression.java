import java.util.Stack;

/**
 * 计算字符串内的表达式 2019年9月24日
 * 
 * @author Administrator
 */
public class ComputeExpression {

	/**
	 * 用来判断符号的优先级
	 * @param s
	 * @return
	 */
	public static int priority(char s) {
		switch (s) {
		case '-':
		case '+':
			return 1;
		case '*':
		case '/':
		case '%':
			return 2;
		//当为数字时
		default:
			return -1;
		}
	}

	/**
	 * 用来计算
	 * @param num1
	 * @param num2
	 * @param s
	 * @return
	 */
	public static double compute(double num1, double num2, char s) {
		switch (s) {
		case '-':
			return num1 - num2;
		case '+':
			return num1 + num2;
		case '%':
			return num1 % num2;
		case '*':
			return num1 * num2;
		case '/':
			return num1 / num2;
		default:
			return 0;

		}
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static double compute(String str) {
		//flag：用来判断操作符栈是否为空
		int  begin = 0, end = 0;
		double result=0;
		
		//num:数字栈    operator:操作符栈
		Stack<Double> num=new Stack<Double>();
		Stack<Character> operator = new Stack<Character>();
		
		//遍历字符串每个字符
		for (int i = 0; i < str.length();i++) {
			char c = str.charAt(i);
			System.out.println(c);
			if(c == '+' || c == '-' || c == '*' || c == '/' || c == '%')
			{
				//System.out.println(begin+"   "+end);
				double d=Double.parseDouble(str.substring(begin, end));
				begin=end+1;
				end=begin;
				num.push(d);
				//当为空时，直接推进操作符栈，不为空时，判断后推入操作符栈或变为空后推入操作符栈
				if(operator.isEmpty())
				{
					operator.push(c);
				}
				else {
					while(priority(c)>0 && priority(c)<=priority(operator.peek()))
					{
						double num2=num.pop();
						double num1=num.pop();
						char o=operator.pop();
						double r=compute(num1, num2, o);
						num.push(r);
						if(operator.isEmpty())
							break;
					}
					operator.push(c);
				}
				/*System.out.println(num.peek());
				System.out.println(operator.peek());*/
			}
			else
				end++;
			
			//最后一个数时
			if(i==str.length()-1)
			{
				double d=Double.parseDouble(str.substring(begin, end));
				num.push(d);
				while(!operator.isEmpty())
				{
					double num2=num.pop();
					double num1=num.pop();
					char o=operator.pop();
					double r=compute(num1, num2, o);
					num.push(r);
				}
				result=num.pop();
				//System.out.println(result);
			}		
		}
		return result;
	}
}
