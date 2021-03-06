package com.example.zinrou_card_select.Collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zinrou_card_select.BaseFragment;
import com.example.zinrou_card_select.CardData;
import com.example.zinrou_card_select.CardDataSave;
import com.example.zinrou_card_select.GachaCardDetailFragment;
import com.example.zinrou_card_select.R;

import java.util.ArrayList;
import java.util.Arrays;


public class SelectedCardPositionFragment extends BaseFragment {
    private Button siminSort;
    private Button zinrouButton;
    private Button sonotaButton;
    private Button allPositionButton;
    private RecyclerView recyclerView;
    private CardData[] selectedCardData;

    public static SelectedCardPositionFragment newInstance() {
        return new SelectedCardPositionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_position_card__recyclerview, container, false);
        siminSort = view.findViewById(R.id.simin_sort);
        zinrouButton = view.findViewById(R.id.zinrou_sort);
        sonotaButton = view.findViewById(R.id.sonota_sort);
        allPositionButton = view.findViewById(R.id.all_position_sort);
        selectedCardData = CardDataSave.getmSelectedCardDataList();

        recyclerView = (RecyclerView) view.findViewById(R.id.container_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.Adapter adapter = new CardAllPositionRecyclerViewAdapter(selectedCardData) {
            @Override
            void onItemClick(View view, int position, CardData[] cardDates) {
                transitionTo(GachaCardDetailFragment.newInstance(cardDates[position], true));
            }
        };
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        siminSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.Adapter adapter = new CardAllPositionRecyclerViewAdapter(CardData.getCamp(selectedCardData, "市民")) {
                    @Override
                    void onItemClick(View view, int position, CardData[] cardDates) {
                        transitionTo(GachaCardDetailFragment.newInstance(selectedCardData[position], true));
                    }
                };
                recyclerView.setAdapter(adapter);
            }
        });

        zinrouButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.Adapter adapter = new CardAllPositionRecyclerViewAdapter(CardData.getCamp(selectedCardData, "人狼")) {
                    @Override
                    void onItemClick(View view, int position, CardData[] cardDates) {
                        transitionTo(GachaCardDetailFragment.newInstance(selectedCardData[position], true));
                    }
                };
                recyclerView.setAdapter(adapter);
            }
        });

        sonotaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardData[] sonotaData = selectedCardData;
                ArrayList<CardData> cardDataList = new ArrayList<CardData>(Arrays.asList(sonotaData));
                ArrayList<CardData> siminZinrouData = new ArrayList<CardData>();
                for (int i = 0; i < selectedCardData.length; i++) {
                    if (selectedCardData[i].getCamp().equals("市民") | selectedCardData[i].getCamp().equals("人狼")) {
                        siminZinrouData.add(selectedCardData[i]);
                    }
                }
                cardDataList.removeAll(siminZinrouData);
                sonotaData = cardDataList.toArray(new CardData[cardDataList.size()]);
                RecyclerView.Adapter adapter = new CardAllPositionRecyclerViewAdapter(sonotaData) {
                    @Override
                    void onItemClick(View view, int position, CardData[] cardDates) {
                        transitionTo(GachaCardDetailFragment.newInstance(selectedCardData[position], true));
                    }
                };
                recyclerView.setAdapter(adapter);
            }
        });

        allPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.Adapter adapter = new CardAllPositionRecyclerViewAdapter(selectedCardData) {
                    @Override
                    void onItemClick(View view, int position, CardData[] cardDates) {
                        transitionTo(GachaCardDetailFragment.newInstance(selectedCardData[position], true));
                    }
                };
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
