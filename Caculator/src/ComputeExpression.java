import java.util.Stack;

/**
 * �����ַ����ڵı��ʽ 2019��9��24��
 * 
 * @author Administrator
 */
public class ComputeExpression {

	/**
	 * �����жϷ��ŵ����ȼ�
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
		//��Ϊ����ʱ
		default:
			return -1;
		}
	}

	/**
	 * ��������
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
		//flag�������жϲ�����ջ�Ƿ�Ϊ��
		int  begin = 0, end = 0;
		double result=0;
		
		//num:����ջ    operator:������ջ
		Stack<Double> num=new Stack<Double>();
		Stack<Character> operator = new Stack<Character>();
		
		//�����ַ���ÿ���ַ�
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
				//��Ϊ��ʱ��ֱ���ƽ�������ջ����Ϊ��ʱ���жϺ����������ջ���Ϊ�պ����������ջ
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
			
			//���һ����ʱ
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
