package com.example.recyclerviewformal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import recyclerview.MyRecyclerViewAdapter;

public class RecyclerViewActivity<MyRecyclerViewAdapter2> extends AppCompatActivity {
    public static Compute  compute = new Compute();
    private Button claer_bt, compute_bt;
    private TextView res_text;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    public static ImageView card1;
    public static ImageView card2;
    public static ImageView card3;
    public static ImageView card4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.RecyclerView);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);


        //初始化数据
        initData();

        //设置recycler view Adapter
        adapter = new MyRecyclerViewAdapter(RecyclerViewActivity.this, cards);


        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyDecoration(-300));


        //设置 recycler view layout manager (indispensable！！）
        recyclerView.setLayoutManager
                (new LinearLayoutManager(
                        RecyclerViewActivity.this,
                        GridLayoutManager.HORIZONTAL,
                        false ));


        /*-------------------------------------*/
        //计算清空
        claer_bt = findViewById(R.id.clear_bt);
        compute_bt = findViewById(R.id.compute_bt);
        res_text = findViewById(R.id.res_text);

        //清空
        claer_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initSelectedImageView();
                for(int i = 0;i < cards.size(); i ++){
                    Card card = cards.get(i);
                    if(card.selected == true){
                        card.selected = false;
                        compute.decreaseCnt();
                        compute.setCheckedNums(card.id, false);
                    }
                }
                res_text.setText("");
                recyclerView.refreshDrawableState();
            }
        });

        //计算
        compute_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res_text.setText("");
                System.out.println("\n--------------checkNums---------------");
                for(int i = 0;i < compute.checkedNums.length; i ++)
                    if(compute.checkedNums[i] > 0)
                        for(int j = 0; j < compute.checkedNums[i]; j ++)
                            System.out.println(i);

                if(compute.getCnt() < 4){
                    Toast.makeText(RecyclerViewActivity.this,
                            "未选够4张！", Toast.LENGTH_SHORT
                            ).show();
                }

                else {
                    try{
                        res_text.setText(compute.Compute24());
                        res_text.setMovementMethod(ScrollingMovementMethod.getInstance());
                    }
                    catch (Exception e){
                        Toast.makeText(RecyclerViewActivity.this,
                                "啊欧,可能发生了除零错误,\n试试换另一些数吧",
                                Toast.LENGTH_SHORT
                                ).show();
                    }
                }


            }
        });






    }






    private void initData() {
        compute.init();

        String[] overTen = new String[]{"J", "Q", "K"};
        String[] type = new String[]{"黑桃", "红桃", "梅花", "方块"};


        for(int i = 1;i <= 13; i++){
            for(int j = 1; j <= 4; j ++){
                Card card = new Card();
                if(i > 10) card.name = type[j - 1] + overTen[i % 10 - 1];
                else if(i == 1) card.name = type[j - 1] + "A";
                else card.name = type[j - 1] + i;
                card.id = i;
                card.idd = i * j;
                cards.add(card);
            }
        }

        cards.get(0).setSrc(R.drawable.a1);
        cards.get(1).setSrc(R.drawable.a2);
        cards.get(2).setSrc(R.drawable.a3);
        cards.get(3).setSrc(R.drawable.a4);
        cards.get(4).setSrc(R.drawable.b1);
        cards.get(5).setSrc(R.drawable.b2);
        cards.get(6).setSrc(R.drawable.b3);
        cards.get(7).setSrc(R.drawable.b4);
        cards.get(8).setSrc(R.drawable.c1);
        cards.get(9).setSrc(R.drawable.c2);
        cards.get(10).setSrc(R.drawable.c3);
        cards.get(11).setSrc(R.drawable.c4);
        cards.get(12).setSrc(R.drawable.d1);
        cards.get(13).setSrc(R.drawable.d2);
        cards.get(14).setSrc(R.drawable.d3);
        cards.get(15).setSrc(R.drawable.d4);
        cards.get(16).setSrc(R.drawable.e1);
        cards.get(17).setSrc(R.drawable.e2);
        cards.get(18).setSrc(R.drawable.e3);
        cards.get(19).setSrc(R.drawable.e4);
        cards.get(20).setSrc(R.drawable.f1);
        cards.get(21).setSrc(R.drawable.f2);
        cards.get(22).setSrc(R.drawable.f3);
        cards.get(23).setSrc(R.drawable.f4);
        cards.get(24).setSrc(R.drawable.g1);
        cards.get(25).setSrc(R.drawable.g2);
        cards.get(26).setSrc(R.drawable.g3);
        cards.get(27).setSrc(R.drawable.g4);
        cards.get(28).setSrc(R.drawable.h1);
        cards.get(29).setSrc(R.drawable.h2);
        cards.get(30).setSrc(R.drawable.h3);
        cards.get(31).setSrc(R.drawable.h4);
        cards.get(32).setSrc(R.drawable.i1);
        cards.get(33).setSrc(R.drawable.i2);
        cards.get(34).setSrc(R.drawable.i3);
        cards.get(35).setSrc(R.drawable.i4);
        cards.get(36).setSrc(R.drawable.j1);
        cards.get(37).setSrc(R.drawable.j2);
        cards.get(38).setSrc(R.drawable.j3);
        cards.get(39).setSrc(R.drawable.j4);
        cards.get(40).setSrc(R.drawable.k1);
        cards.get(41).setSrc(R.drawable.k2);
        cards.get(42).setSrc(R.drawable.k3);
        cards.get(43).setSrc(R.drawable.k4);
        cards.get(44).setSrc(R.drawable.l1);
        cards.get(45).setSrc(R.drawable.l2);
        cards.get(46).setSrc(R.drawable.l3);
        cards.get(47).setSrc(R.drawable.l4);
        cards.get(48).setSrc(R.drawable.m1);
        cards.get(49).setSrc(R.drawable.m2);
        cards.get(50).setSrc(R.drawable.m3);
        cards.get(51).setSrc(R.drawable.m4);

        initSelectedImageView();

    }

    public static int[] selectedCardsPos = new int[60]; //看看卡片x对应的imageview是哪个
    public static boolean[] imageExists = new boolean[60]; //放已选择卡片的方框里，哪些已经有卡片了
    private void initSelectedImageView() {
        for(int i = 0; i < selectedCardsPos.length; i ++) selectedCardsPos[i] = -1;
        for(int i = 0;i < imageExists.length; i ++) imageExists[i] = false;
        card1.setImageResource(R.drawable.add_image2);
        card2.setImageResource(R.drawable.add_image2);
        card3.setImageResource(R.drawable.add_image2);
        card4.setImageResource(R.drawable.add_image2);
    }

    public static ImageView getWhichPos(Card card){
        if(selectedCardsPos[card.idd] == 1) return card1;
        else if(selectedCardsPos[card.idd] == 2) return card2;
        else if(selectedCardsPos[card.idd] == 3) return card3;
        else if(selectedCardsPos[card.idd] == 4) return card4;
        return null;
    };


}