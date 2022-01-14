package com.example.recyclerviewformal;

import java.util.ArrayList;
import java.util.List;


public class Compute {
    public int[] checkedNums = new int[60];
    private int cnt = 0;

    public void setCheckedNums(int num, boolean increase){
        checkedNums[num] = increase ? checkedNums[num] + 1 : checkedNums[num] - 1;

    }

    public void init(){
        for(int i = 0;i < checkedNums.length; i++)
            checkedNums[i] = 0;
    }


    public int getCnt() {
        return cnt;
    }

    public void increaseCnt() {
        this.cnt = cnt + 1;
    }

    public void decreaseCnt(){
        cnt --;
    }



//    public String computeRes(){
//        int k = 0;
//
//
//        for(int i = 0;i < checkedNums.length; i ++) {
//            if (checkedNums[i] > 0) {
//                for (int j = 0; j < checkedNums[i]; j++)
//                    tar[++k] = i;
//            }
//        }
//
//        System.out.println("\n---------tar--------");
//        for(int i = 1;i <= 4; i++) System.out.print(" " + tar[i]);
//        System.out.println("\n-----------------\n");
//
//        return  Compute24();
//    }





    /*----------------------------------------------------*/
    private boolean found = false;
    private int N = 60;
    private boolean[] st = new boolean[N];
    private int[] num = new int[N];
    private int[] pk = new int[N];   //pk数组用来储存随机排布的扑克牌序列
    private int[] tar = new int[5];
    private boolean[] use = new boolean[5];
    private String[] op = new String[]{" ", "+", "-", "*", "/"};
    private String[] path = new String[30];
    private int u = 1;
    private static String resText = new String("");

    public String checkTar(){
        String res =  "";
        for(int i = 1;i < tar.length; i++) res += tar[i] + ", ";
        return res;
    }



   public String Compute24(){
        //计算之前初始化数据
        resText = ""; //结果
        found = false;
       int k = 0;
       for(int i = 0;i < checkedNums.length; i ++) {
           if (checkedNums[i] > 0) {
               for (int j = 0; j < checkedNums[i]; j++)
                   tar[++k] = i;
           }
       }

       //初始化完成

       System.out.println("\n---------tar--------");
       for(int i = 1;i <= 4; i++) System.out.print(" " + tar[i]);
       System.out.println("\n-----------------\n");

        //---------------计算24，tar是四张牌------------------
       for(int i = 1; i <= 4; i++){
           for(int j = 1; j <= 4; j++) use[j] = false;
           for(int j = 1; j <= 20; j++) path[j] = "";
           use[i] = true;
           u = 2;
           path[1] = "" + tar[i];
           dfs(tar[i], u);

       }
       dfs2();

       //--------------------计算24完成--------------------

       if(!found) resText = "不能得到24！";
       return resText;
   }


    static boolean dv(int a, int b) {
        if (a < b || (a > b && a % b != 0)) return false;
        else if(a > b && a % b == 0) return true;
        return false;
    }

    static int get(int i, int a, int b) {//i取值不同代表加减乘除
        if (i == 1) return a + b;
        else if (i == 2) return a - b;
        else if (i == 3) return a * b;
        else return a / b;
    }

    private void dfs2(){
        //for(int i = 1; i <= 20; i++) path[i] = "";
        for(int i = 1; i <= 4; i++) use[i] = false;
        int res1, res2 ;
        for(int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (j != i ){
                    use[i] = use[j] = true;

                    int x1 = 1, x2 = 1; //找剩下的两个数
                    for (int ii = 1; ii <= 4; ii++)
                        if (!use[ii]) {
                            x1 = ii;
                            use[ii] = true;
                            break;
                        }

                    for (int ii = 1; ii <= 4; ii++)
                        if (!use[ii]) {
                            x2 = ii;
                            use[ii] = true;
                            break;
                        }

                    //找i，j 和 x1, x2的操作数
                    int opr1, opr2, rvopr2;
                    if (dv(tar[i], tar[j])) opr1 = 4;
                    else opr1 = 3;

                    if (dv(tar[x1], tar[x2])) opr2 = 4;
                    else opr2 = 3;

                    if (dv(tar[x2], tar[x1])) rvopr2 = 4;
                    else rvopr2 = 3;

                    for (int p1 = 1; p1 <= opr1; p1++) { // 枚举i ，j 的操作
                        res1 = get(p1, tar[i], tar[j]);


                        for (int p2 = 1; p2 <= opr2; p2++) { // x1 -> x2，枚举操作
                            res2 = get(p2, tar[x1], tar[x2]);

                            int opr3;
                            if (dv(res1, res2)) opr3 = 4;
                            else opr3 = 3;
                            //枚举res1 和 res2 的操作
                            for (int p3 = 1; p3 <= opr3; p3++) {
                                if (get(p3, res1, res2) == 24) {
                                    found = true;
                                    System.out.println(tar[i] + op[p1] + tar[j] + ") " + op[p3] + " (" + tar[x1] + op[p2] + tar[x2]);
                                    resText += tar[i] + op[p1] + tar[j] + ") " + op[p3] + " (" + tar[x1] + op[p2] + tar[x2] + " = 24" + "\n";
                                }
                            }

                        }

                    }

                    use[i] = use[j] = use[x1] = use[x2] = false;
                }

            }


        }
    }




    private void dfs(int res, int u) {
        boolean useall = true;

        for (int i = 1; i <= 4; i++) {
            if (!use[i]) useall = false;
        }

        if (res == 24 && useall) {
            for (int i = 1; path[i] != ""; i++) {
                System.out.print(path[i]);
                resText += path[i]; /*****************/
            }
            resText += " = 24\n";/******************/
            System.out.print("\n");
            found =  true;
            return;
        }

        if (res != 24 && useall) return;

        //枚举哪个数没用过
        for (int i = 1; i <= 4; i++) {
            if (!use[i]) {
                use[i] = true;
                int k; // k代表res和当前数能进行的操作种类
                if (dv(res, tar[i])) k = 4;
                else k = 3;
                for (int j = 1; j <= k; j++) {
                    int t = res, r = u;
                    if(j == 4 && !dv(res,tar[i])) break;
                    else
                        res = get(j, res, tar[i]);
                    path[u++] = op[j];
                    path[u++] = "" + tar[i];
                    path[u++] = ")";
                    dfs(res, u);
                    path[r] = "";
                    path[r + 1] = "";
                    path[r + 2] = "";
                    res = t;
                    u = r;
                }

                use[i] = false;
            }
        }


    }


}
