package com.example.zakat.views.core;

import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.models.core.ZakatArticle;

public interface OnClickListeners {
    void onItemClick(ZakatArticle article, int position);

    public interface OnClickListenersAdmin{
        void onItemClick(ApplicationToSubmit application,int position);
    }
}


