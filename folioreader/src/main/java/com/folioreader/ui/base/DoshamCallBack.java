package com.folioreader.ui.base;

import com.folioreader.model.dictionary.Dosh;

public interface DoshamCallBack  extends  BaseMvpView{
    void onDoshamDataReceived(Dosh word);
}
