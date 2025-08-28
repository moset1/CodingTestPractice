package P16637;


import java.util.*;

/*
길이가 N 인 수식

괄호를 적절히 추가해서 구할 수 있는 최댓값을 구해야한다.

수식의 길이는 최대 19
수의 갯수는 최대 10개
경우의 수는 100만 이하?

중복 괄호는 안된다.

사실상 계산 순서를 어떻게 할지만 정해주면 된다.



 */
public class P16637 {


    static int N;
    static long max=Long.MIN_VALUE;
    static char[] exp;
    static List<Long> nums;
    static List<Character> ops;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        exp = sc.next().toCharArray();
        nums = new ArrayList<>();
        ops = new ArrayList<>();

        for(int i=0; i<exp.length; i++) {
            if (i%2==0) {
                nums.add((Long.valueOf(exp[i]+"")));
            } else {
                ops.add(exp[i]);
            }
        }

        calculate(nums.get(0), 0);
        System.out.println(max);

    }

    static void calculate( long num, int idx) {
        if (idx >= N/2 ) {
            max = Math.max(max, num);
            return;
        }

        calculate(applyop(num, nums.get(idx +1), ops.get(idx)), idx+1);
        if (idx < (N/2-1)) calculate(applyop(num, applyop(nums.get(idx+1),nums.get(idx+2),ops.get(idx+1)), ops.get(idx)), idx+2);

    }

    static long applyop(long num1, long num2, char op) {

        //System.out.println( "num1 = " + num1 + " num2 = " +  num2 + " op = " + op);

        long ret = 0;
        if(op == '+') {
            ret = num1 + num2;
        } else if (op == '-') {
            ret =  num1 - num2;
        } else if (op == '*') {
            ret = num1 * num2;
        }

        return ret;
    }


}
