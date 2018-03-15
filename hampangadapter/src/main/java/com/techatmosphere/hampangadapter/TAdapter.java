package com.techatmosphere.hampangadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nirwannursabda
 * @date 14/03/18
 */

public class TAdapter<V, W extends ViewDataBinding>
        extends RecyclerView.Adapter<TAdapter.ViewHolder>
        implements TNotify, Filterable
{
    private List<V> mValues;
    private List<V> listItemAll = new ArrayList<>();
    private int itemLayout;
    private Class typeParameterClass;
    private TFilter filter;

    public TAdapter(){
        this.mValues = Collections.emptyList();
    }

    public TAdapter(int itemLayout, Class typeParameterClass){
        this.mValues = Collections.emptyList();
        this.itemLayout = itemLayout;
        this.typeParameterClass = typeParameterClass;
    }

    public TAdapter(List<V> items, int itemLayout, Class typeParameterClass) {
        mValues = items;
        this.itemLayout = itemLayout;
        this.typeParameterClass = typeParameterClass;
    }

    public TAdapter addItemLayout(int itemLayout){
        this.itemLayout = itemLayout;
        return this;
    }

    public TAdapter addTypeParameterClass(Class typeParameterClass){
        this.typeParameterClass = typeParameterClass;
        return this;
    }

    public TNotify getNotify(){
        return this;
    }

    public void addFilterRule(TFilter filter){
        this.filter = filter;
    }

    @Override
    public TAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        W itemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext())
                        , this.itemLayout, parent, false);

        return new TAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final TAdapter.ViewHolder holder, int position) {
        holder.bindItem(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

//    public void setHotList(List<V> hotList){
//        this.mValues = hotList;
//        notifyDataSetChanged();
//    }

    public void setListItem(List<V> d){
        mValues = d;
    }

    public void updateItemAll(List<V> d){
        listItemAll.clear();
        listItemAll.addAll(d);
    }

    public void updateListItemAll(List<V> d){
        listItemAll.clear();
        listItemAll.addAll(d);
        filter.updateFilterItem(d);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        W binding;

        public ViewHolder(W binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindItem(V item){
            try {
                Object me = binding.getClass().getMethod("getViewModel").invoke(binding);
                if(me == null) {

                    Object obj = typeParameterClass.newInstance();
                    obj.getClass().getMethod("setModel", item.getClass()).invoke(obj, item);

                    binding.getClass().getMethod("setViewModel", obj.getClass()).invoke(binding, obj);

                }else {
                    Object obj = binding.getClass().getMethod("getViewModel").invoke(binding);
                    obj.getClass().getMethod("setModel", item.getClass()).invoke(obj, item);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Filter getFilter() {
        return this.filter;
    }

    public void notify(Object obj){
        mValues = (ArrayList<V>) obj;
        notifyDataSetChanged();
    }

}