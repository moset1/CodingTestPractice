package P3425;

import java.io.*;
import java.util.*;

public class P3425 {
    static LinkedList<Long> goStack;
    static ArrayList<String> program;
    static ArrayList<Integer> input;
    static int N;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        goStack = new LinkedList<>();
        program = new ArrayList<>();
        input = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        long result;
        boolean notError;
        String op;
        op = st.nextToken();
        if (st.hasMoreTokens()) {
            op += " " + st.nextToken();
        }
        while(!op.equals("QUIT")){
            program.add(op);

            if(!op.equals("END")){
                st = new StringTokenizer(br.readLine());
                op = st.nextToken();
                while(!op.equals("END")){

                    if(st.hasMoreTokens()){
                        op += " " + st.nextToken();
                    }
                    program.add(op);
                    st = new StringTokenizer(br.readLine());
                    op = st.nextToken();
                    if (st.hasMoreTokens()) {
                        op += " " + st.nextToken();
                    }


                }
            }

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                long n = Long.parseLong(st.nextToken());
                goStack.push(n);
                notError = true;
                for(int j=0; j<program.size(); j++) {

                    notError = operationOfGostack(program.get(j));
                    if (notError == false) {
                        sb.append("ERROR");
                        sb.append("\n");
                        break;
                    }
                }

                if(notError == true) {
                    if ( goStack.size() == 0 || goStack.size()>1 || Math.abs(goStack.get(0)) > 1000000000) {
                        sb.append("ERROR");
                        sb.append("\n");

                    } else {
                        result = goStack.pop();
                        sb.append(result);
                        sb.append("\n");

                    }
                }

                goStack.clear();

            }
            sb.append("\n");

            // 빈줄
            st = new StringTokenizer(br.readLine());
            st = new StringTokenizer(br.readLine());

            op = st.nextToken();
            if (st.hasMoreTokens()) {
                op += " " + st.nextToken();
            }

            program.clear();
            goStack.clear();
            input.clear();


        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }

    static boolean operationOfGostack(String op) {
        StringTokenizer st = new StringTokenizer(op);
        long X;
        long A,B;
        long result;
        // 에러처리하기
        switch( st.nextToken()) {
            case "NUM":
                X = Integer.parseInt(st.nextToken());
                goStack.push(X);
                break;
            case "POP":
                if(goStack.size()>=1) {
                    goStack.removeFirst();
                } else {
                    return false;
                }

                break;
            case "INV":
                if(goStack.size()>=1 ) {
                    X = goStack.getFirst() * -1;
                    goStack.set(0, X);
                } else {
                    return false;
                }

                break;
            case "DUP":
                if(goStack.size()>=1) {
                    X = goStack.getFirst();
                    goStack.push(X);
                } else {
                    return false;
                }

                break;
            case "SWP":
                if(goStack.size()>=2) {
                    X = goStack.getFirst();
                    goStack.set(0,goStack.get(1));
                    goStack.set(1, X);
                } else {
                    return false;
                }

                break;
            case "ADD":

                if(goStack.size()>=2) {
                    A = goStack.pop();
                    B = goStack.pop();

                } else {

                    return false;
                }

                goStack.push(A+B);
                break;
            case "SUB":
                if(goStack.size()>=2) {
                    A = goStack.pop();
                    B = goStack.pop();

                } else {

                    return false;
                }
                goStack.push(B-A);
                break;
            case "MUL":
                if(goStack.size()>=2) {
                    A = goStack.pop();
                    B = goStack.pop();

                } else {

                    return false;
                }
                goStack.push(B*A);
                break;
            case "DIV":
                if(goStack.size()>=2) {
                    A = goStack.pop();
                    B = goStack.pop();


                } else {

                    return false;
                }

                if(A == 0) {

                    return false;
                }
                if(A < 0 && B < 0) {
                    result = Math.abs(B) / Math.abs(A);
                } else if ( A> 0 && B > 0) {
                    result = B/A;
                } else {
                    result = Math.abs(B) / Math.abs(A);
                    result *= -1;

                }
                goStack.push(result);
                break;
            case "MOD":
                if(goStack.size()>=2) {
                    A = goStack.pop();
                    B = goStack.pop();

                } else {

                    return false;
                }
                if(A == 0 ) {

                    return false;
                }
                result = Math.abs(B) % Math.abs(A);
                if(B<0) {
                    result *= -1;
                }
                goStack.push(result);
                break;
            case "END":

                break;



        }


        return true;

    }


}
