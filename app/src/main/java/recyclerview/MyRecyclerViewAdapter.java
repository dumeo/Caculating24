package recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewformal.Card;
import com.example.recyclerviewformal.R;
import com.example.recyclerviewformal.RecyclerViewActivity;

import java.util.ArrayList;




public class MyRecyclerViewAdapter extends RecyclerView.Adapter
        <MyRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Card> cards; //扑克牌数据
    private ImageView card1, card2, card3, card4; //用来存放已选的图片

    public MyRecyclerViewAdapter(Context context, ArrayList<Card> cards){
        this.context = context;
        this.cards = cards;
    }


    /*
    * 创建view和view holder
    * */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //去hold这个context，用R.layout....来填充
        View itemView = View.inflate(context, R.layout.item_recyclerview, null);
        return new MyViewHolder(itemView);
    }



    /*
    * 数据和view绑定
    * */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            //根据位置得到对应的数据
           //String data = datas.get(position);
           Card card = cards.get(position);
           holder.tv_title.setText(card.name);
           holder.iv_icon.setImageResource(card.src);
           if(card.selected == true)
                holder.checkBox.setImageResource(R.drawable.alredy_check);

           else holder.checkBox.setImageResource(R.drawable.blank_square);
    }


    /*
    * 得到数据总条数
    * */
    @Override
    public int getItemCount() {
        return cards.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        //这里实例化的是item_recyclerview里面的东西
        private ImageView iv_icon;
        private TextView tv_title;
        private ImageView checkBox;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
            checkBox = itemView.findViewById(R.id.check_box);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Card card = cards.get(getLayoutPosition());
//                    String msg  = new String("");

                    if(card.selected == true){
                        RecyclerViewActivity.compute.setCheckedNums(card.id, false);
                        checkBox.setImageResource(R.drawable.blank_square);
                        card.selected = false;
                        RecyclerViewActivity.compute.decreaseCnt();
//                        msg = "丢弃" + card.name;
                        //撤销显示图片
                        int cardsPos = RecyclerViewActivity.selectedCardsPos[card.idd];
                        System.out.println("-----------cardPos----------------");
                        System.out.println(cardsPos);
                        ImageView selectedView = RecyclerViewActivity.getWhichPos(card);
                        if(selectedView != null){
                            selectedView.setImageResource(R.drawable.add_image2);
                            RecyclerViewActivity.imageExists[cardsPos] = false;
                            RecyclerViewActivity.selectedCardsPos[card.idd] = -1;
                        }

                        else System.out.println("SelectedView = null, Not found...............102");

                        //end撤销显示图片

                    }

                    else if(card.selected == false &&
                            RecyclerViewActivity.compute.getCnt() < 4){
                        RecyclerViewActivity.compute.setCheckedNums(card.id, true);
                        checkBox.setImageResource(R.drawable.alredy_check);
                        card.selected = true;
                        RecyclerViewActivity.compute.increaseCnt();
//                        msg = card.name;

                        //将已选图片放到指定位置
                        for(int i = 1;i <= 4; i ++){
                            if(RecyclerViewActivity.imageExists[i] == false){
                                if(i == 1) {
                                    RecyclerViewActivity.card1.setImageResource(card.getSrc());
                                    RecyclerViewActivity.selectedCardsPos[card.idd] = 1;
                                    RecyclerViewActivity.imageExists[1] = true;
                                }

                                else if(i == 2){
                                    RecyclerViewActivity.card2.setImageResource(card.getSrc());
                                    RecyclerViewActivity.selectedCardsPos[card.idd] = 2;
                                    RecyclerViewActivity.imageExists[2] = true;

                                }

                                else if(i == 3){
                                    RecyclerViewActivity.card3.setImageResource(card.getSrc());
                                    RecyclerViewActivity.selectedCardsPos[card.idd] = 3;
                                    RecyclerViewActivity.imageExists[3] = true;

                                }

                                else if(i == 4){
                                    RecyclerViewActivity.card4.setImageResource(card.getSrc());
                                    RecyclerViewActivity.selectedCardsPos[card.idd] = 4;
                                    RecyclerViewActivity.imageExists[4] = true;

                                }


                              break;

                            }
                        }//end将已选图片放到指定位置



                    }


                }
            });

        }
    }
}
