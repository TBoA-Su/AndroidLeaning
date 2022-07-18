package com.tboa.learning06_test;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Integer> score1;
    private MutableLiveData<Integer> score2;
    private int id = 1;
    private int score_p = 0;

    public MutableLiveData<Integer> getScore1() {
        if (score1 == null) {
            score1 = new MutableLiveData<>();
            score1.setValue(0);
        }
        return score1;
    }

    public MutableLiveData<Integer> getScore2() {
        if (score2 == null) {
            score2 = new MutableLiveData<>();
            score2.setValue(0);
        }
        return score2;
    }

    public void add(int id, int score) {
        this.id = id;
        switch (id) {
            case 1:
                score_p = getScore1().getValue();
                score1.setValue(score_p + score);
                break;
            case 2:
                score_p = getScore2().getValue();
                score2.setValue(score_p + score);
                break;
            default:
                break;
        }
    }

    public void redo() {
        switch (id) {
            case 1:
                if (score1 == null) score1 = new MutableLiveData<>();
                score1.setValue(score_p);
                break;
            case 2:
                if (score2 == null) score2 = new MutableLiveData<>();
                score2.setValue(score_p);
                break;
            default:
                break;
        }
    }

    public void restart() {
        if (score1 == null) score1 = new MutableLiveData<>();
        score1.setValue(0);
        if (score2 == null) score2 = new MutableLiveData<>();
        score2.setValue(0);
    }
}
