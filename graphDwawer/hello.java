package test;

import java.util.*;
/**
 * 应用连续立方和的公式 1^3+2^3+......+n^3=(n(n+1)/2)^2;  
 */
public class hello {
    public static void main(String[] args) {
        int choice;
        Scanner cin=new Scanner(System.in);
        System.out.println("需要进行寻找这样的数 输入'0'\n");
        System.out.println("需要进行验证输入 '1'\n");
        choice=cin.nextInt();
        if(choice==0){
            System.out.println("输入一个数作为最大范围\n");
            int end=cin.nextInt();
            search(end);
        }
        else{
            System.out.println("输入需要验证的式子左式的起始和结束，再输入右式的数\n");
            //例如3 5 6 即 3^3+4^3+5^3=6^3
            int begin=cin.nextInt();
            int end=cin.nextInt();
            int result=cin.nextInt();
            int ans=judge(begin, end, result);
            if(ans==1) System.out.println("验证成功\n");
            else System.out.println("验证失败\n");
        }
        
    }
    static long sum(int n){
        long p=n*(n+1)/2;
        long ans=p*p;
        return ans;
    }
    static void search(int p){
        long begin=0,end=0,ans=0;
        for(int i=0;i<=p;++i){
            for(int j=i+1;j<=p;++j){
                begin=sum(i);
                end=sum(j);
                ans=end-begin;
                for(int k=j+1;k<p;++k){
                    long judgenum=k*k*k;
                    if(judgenum==ans){
                        System.out.println((i+1)+"^3+......+"+j+"^3="+k+"^3\n");
                    }
                }
            }
        }
        return;
    }
    
    static int judge(int begin,int end,int result){
        int m=0;
        int a=end*(end+1)/2;
        int b=(begin-1)*(begin)/2;
        int c=a*a-b*b;
        int d=result*result*result;
        if(c==d) m=1;
        else m=0;
        return m;
    }

}
