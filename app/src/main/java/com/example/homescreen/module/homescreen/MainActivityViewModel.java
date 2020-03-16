package com.example.homescreen.module.homescreen;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homescreen.adapter.ListAdapter;
import com.example.homescreen.model.HeaderModel;
import com.example.homescreen.model.Items;
import com.example.homescreen.network.Api;
import com.example.homescreen.network.ApiCleint;
import com.example.homescreen.room_db.DBRepository;
import com.example.homescreen.shared_preferences.SharedPreferenceUtils;
import com.example.homescreen.shared_preferences.SharedValues;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivityViewModel extends AndroidViewModel {

    Context mContext;
    private static Api apiInterface;

    public ObservableArrayList<Items> items = new ObservableArrayList<>();

    private static Retrofit mRetrofit_base;
    public ObservableField<String> toolbar_title = new ObservableField<>();
    MutableLiveData<Boolean> showLoadingDialog = new MutableLiveData<>();
    private MutableLiveData<List<Items>> itemList = new MutableLiveData<>();
    private ListAdapter itemAdapter;
    SharedPreferenceUtils mPreference;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.mContext = application;
        mRetrofit_base = ApiCleint.getClient();
        apiInterface = mRetrofit_base.create(Api.class);
        itemAdapter = new ListAdapter(application , this);
        mPreference = new SharedPreferenceUtils(application);
        toolbar_title.set(mPreference.getStringValue(SharedValues.TOOLBAR_TITLE, ""));
        showLoadingDialog.setValue(false);
//        getListFromApi();
        /**API is called  only once id database is empty.  API can be called from clicking the sync button in the toolbar*/
        getItemsFromDB();
    }

    public MutableLiveData<List<Items>> getItemList() {
        return itemList;
    }

    public void setItemList(MutableLiveData<List<Items>> itemList) {
        this.itemList = itemList;
    }

    public LinearLayoutManager getLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,  false);
        return layoutManager;
    }


    public ListAdapter getItemAdapter() {
        return itemAdapter;
    }

    public void setItemAdapter(ListAdapter itemAdapter) {
        this.itemAdapter = itemAdapter;
    }

    public void updateAdapter(List<Items> items) {
        itemAdapter.updateData(items);
//        this.itemAdapter.notifyDataSetChanged();
    }


    private void getItemsFromDB() {

        getItems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Items>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Items> staffPojo) {
                        if(staffPojo!=null && staffPojo.size()>0) {
                            showLoadingDialog.setValue(false);
                            itemList.setValue(staffPojo);
                            setItemList(itemList);
                        }else{
                            getListFromApi();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("units", "fail  = " + e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public Observable<List<Items>> getItems() {
        return Observable.create(subscriber -> {
            subscriber.onNext(  DBRepository.getInstance(mContext).getItems());
        });
    }

    private void getListFromApi() {
        Observable<HeaderModel> companyListObservable = apiInterface.getItems();
        companyListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HeaderModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HeaderModel headerModel) {
                        if(headerModel!=null && headerModel.getRows()!=null){
                            mPreference.setValue(SharedValues.TOOLBAR_TITLE, headerModel.getTitle());
                            toolbar_title.set(headerModel.getTitle());
//                            itemList.setValue(headerModel.getRows());
//                            setItemList(itemList);
                            insertToDB(headerModel.getRows());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Responseget", e + " ------error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    
    private void insertToDB(List<Items> rows) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                DBRepository.getInstance(mContext).insetItems(rows);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getItemsFromDB();
                        Log.d("discount", " ------itemDiscount");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("discount", e + " ------error");
                    }
                });
    }


    public void onSyncBtnClicked() {
        showLoadingDialog.setValue(true);
        deletedTable();
    }

    private void deletedTable() {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                DBRepository.getInstance(mContext).deleteItems();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getListFromApi();
                        Log.d("discount", " ------itemDiscount");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("discount", e + " ------error");
                    }
                });
    }

    public void onItemClicked(int adapterPosition, Items items) {
        Toast.makeText(mContext,"position--"+ adapterPosition, Toast.LENGTH_SHORT).show();
    }
}
