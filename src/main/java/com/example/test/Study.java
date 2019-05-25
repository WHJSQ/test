package com.example.test;

import java.util.Stack;

/**
 * Created by Administrator on 2019/5/18.
 */
public class Study {

    public static void main(String[] args) {
       // System.out.println(removeSpecialChar("bbdfg"));
       // System.out.println(removeSpecialChar("ghghacbdsfds"));
        //System.out.println(removeSpecialChar("dfaaabbbcccdfdf"));
        printArr(new int[]{7,2,3,8,6,9,1});
    }

    //去除字符串中的"ac" "b"字符
    public static String removeSpecialChar(String str){
        if(str==null || str.equals("")) return str;
        Stack<Character> s = new Stack<>();
        for(int i=0; i<str.length(); ){
            char c = str.charAt(i);
            if(c == 'b') {
                i++;
            }
            else if(c != 'c'){
                s.push(c);
                i++;
            }else if(c == 'c'){
                if(!s.isEmpty() && s.peek()=='a'){
                    s.pop();
                    i++;
                }else{
                    s.push(c);
                    i++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!s.isEmpty()){
            sb.append(s.pop());
        }
        return sb.reverse().toString();
    }

    public static void printArr(int[] arr){
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<arr.length; i++){
            if(s.isEmpty()) s.push(arr[i]);
            else{
                while(!s.isEmpty()&&s.peek()<arr[i]){
                    System.out.println(s.pop() + " , " + arr[i]);
                }
                s.push(arr[i]);
            }
        }
        while(!s.isEmpty()){
            System.out.println(s.pop() + " , " + "-1");
        }
    }

}

