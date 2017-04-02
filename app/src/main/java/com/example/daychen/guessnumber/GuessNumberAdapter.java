package com.example.daychen.guessnumber;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by daychen on 2017/4/2.
 */

public class GuessNumberAdapter extends RecyclerView.Adapter<GuessNumberAdapter.GuessNumberAdapterViewHolder> {
    private int[] mGuessNumber;
    private int[] mGuessNumberColor;
    private int computeAnswer;
    private int totalNumber;

    private final GuessNumberAdapterOnClickHandler mClickHandler;

    public interface GuessNumberAdapterOnClickHandler {
        void onClick(int guessThisNumber);
    }

    public GuessNumberAdapter(GuessNumberAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class GuessNumberAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mGuessNumberTextView;

        public GuessNumberAdapterViewHolder(View view){
            super(view);
            mGuessNumberTextView = (TextView) view.findViewById(R.id.guess_number_data);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view){
            int adapterPosition = getAdapterPosition();
            int guessThisNumber = mGuessNumber[adapterPosition];
            guessTheNumber(guessThisNumber);
            mClickHandler.onClick(guessThisNumber);
        }
        private void guessTheNumber(int number){
            if (number == computeAnswer){
                mGuessNumberColor[number-1] = Color.rgb(255,0,0);
            }
            else if(number > computeAnswer){
                for (int i = number; i <= totalNumber; i++){
                    mGuessNumberColor[i-1] = Color.rgb(180,180,180);
                }
            }
            else if(number < computeAnswer){
                for (int i = 1; i <= number; i++){
                    mGuessNumberColor[i-1] = Color.rgb(180,180,180);
                }
            }
            notifyDataSetChanged();
        }
    }


    @Override
    public GuessNumberAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int LayoutIdForListItem = R.layout.guess_number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(LayoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new GuessNumberAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuessNumberAdapterViewHolder guessNumberAdapterViewHolder, int position) {
        int guessForThisNumber = mGuessNumber[position];
        guessNumberAdapterViewHolder.mGuessNumberTextView.setBackgroundColor(mGuessNumberColor[position]);
        guessNumberAdapterViewHolder.mGuessNumberTextView.setText(Integer.toString(guessForThisNumber));
    }

    @Override
    public int getItemCount() {
        if (null == mGuessNumber) return 0;
        return mGuessNumber.length;
    }

    public void setGuessNumber(int total, int computeAns) {
        totalNumber = total;
        int[] t = new int[totalNumber];
        int[] color_t = new int[totalNumber];
        for (int i = 1; i <= totalNumber; i++){
            t[i-1] = i;
            color_t[i-1] = Color.rgb(255,255,255);
        }
        computeAnswer = computeAns;
        mGuessNumber = t;
        mGuessNumberColor = color_t;
        notifyDataSetChanged();
    }
    public void setGuessNumber() {
        int[] t = null;
        int[] color_t = null;
        mGuessNumber = t;
        mGuessNumberColor = color_t;
        totalNumber = 0;
        notifyDataSetChanged();
    }

}
