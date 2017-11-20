package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/*
    버튼을 이용하여 전위표기로 사칙연산식을 입력받아 후위표기식으로 변환 후 연산결과 출력하는 프로그램
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static String text = "0";
    static Integer answer;
    static Character[] arr;

    public void onClick(View view) {
        Button b = (Button)view;
        if(text == "0")
            text = b.getText().toString();
        else
            text += b.getText().toString();

        TextView textView = (TextView) findViewById(R.id.Cal_text);
        textView.setText(text);
    }

    // 초기화 버튼
    public void setInitialize(View view){
        TextView textView = (TextView) findViewById(R.id.Cal_text);

        text = "0";
        textView.setText(text);
    }

    // 계산 시작
    public void getResult(View view){
        IStack s = new IStack();
        // 중위표기식을 후위표기식으로 변환
        s.infix_to_postfix(text);

        int a,b;
        char[] arr = text.toCharArray();

        for(int i = 0 ; i <  text.length(); ++ i)
        {
            Character item = arr[i];


            if( item >= '0' && item <= '9' )
            {
                stack.add(Integer.parseInt(item.toString()));
            }else{
                b = stack.pop();
                a = stack.pop();
                if(item == '+'){
                    stack.push((a+b));
                }else if(item == '-'){
                    stack.push((a-b));
                }else if(item == '*'){
                    stack.push((a*b));
                }else if(item == '/'){
                    stack.push((a/b));
                }
            }
        }

        answer = stack.pop();

        DecimalFormat df = new DecimalFormat("#.#####");
        TextView textView = (TextView) findViewById(R.id.Cal_text);
        textView.setText(Integer.toString(answer));

    }

    class IStack extends Stack<Character> {

        // 연산자 우선순위 반환
        int prec(char op) {
            switch(op) {
                case '(': case ')': return 0;
                case '+': case '-': return 1;
                case '*': case '/': return 2;
            }
            return -1;
        }

        // 중위 표기 수식 -> 후위 표기 수식
        void infix_to_postfix(String exp) {
            char[] ch_exp = exp.toCharArray();
            char ch, top_op;

            for (int i = 0; i < ch_exp.length; i++) {
                ch = ch_exp[i];
                switch(ch) {
                    case '+': case '-': case '*': case '/': // 연산자
                        push(ch);
                        break;
                    case '(': // 왼쪽 괄호
                        push(ch);
                        break;
                    case ')': // 오른쪽 괄호
                        top_op = pop();
                        // 왼쪽 괄호
                        while(top_op != '(') {
                            top_op = pop();
                        }
                        break;
                    default: // 피연산자
                        break;
                }
            }
            int idx = 0;
            while(!is_empty()) { // 스택에 저장된 연산자들 출력
                arr[idx] = pop();
                idx++;
            }
        }

    }


    public void exit(View view){
        finish();
    }
}
