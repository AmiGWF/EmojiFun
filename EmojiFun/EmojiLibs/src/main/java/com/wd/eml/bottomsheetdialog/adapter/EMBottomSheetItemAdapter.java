package com.wd.eml.bottomsheetdialog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.eml.R;
import com.wd.eml.bottomsheetdialog.EMBottomSheetBuilder;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetDivider;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetHeader;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItem;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItemClickListener;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetMenuItem;

import java.util.List;

/**
 * Author : wudu
 * Time : 2017/8/19.
 * Tips :
 */

public class EMBottomSheetItemAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER= 1;
    private static final int TYPE_DIVIDER = 2;

    private int mode;
    private int mGridItemWidth;
    private List<BottomSheetItem> sheetItems;
    private BottomSheetItemClickListener sheetItemClickListener;

    public EMBottomSheetItemAdapter(int mode, List<BottomSheetItem> sheetItems, BottomSheetItemClickListener sheetItemClickListener) {
        this.mode = mode;
        this.sheetItems = sheetItems;
        this.sheetItemClickListener = sheetItemClickListener;
    }

    public void setmGridItemWidth(int mGridItemWidth) {
        this.mGridItemWidth = mGridItemWidth;
    }

    public void setSheetItemClickListener(BottomSheetItemClickListener sheetItemClickListener) {
        this.sheetItemClickListener = sheetItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mode == EMBottomSheetBuilder.MODE_LIST){
           if(viewType == TYPE_ITEM){
               return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottomsheetdialog_list_adapter,parent,false));
           }else if(viewType == TYPE_HEADER){
               return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottomsheetdialog_list_adapter,parent,false));
           }else if(viewType == TYPE_DIVIDER){
               return new DividerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottomsheetdialog_list_adapter,parent,false));
           }else {
               throw new IllegalArgumentException("EMBottomSheetItemAdapter,the viewType is not allow");
           }
        }else if(mode == EMBottomSheetBuilder.MODE_GRID){
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottomsheetdialog_list_adapter,parent,false);
            ViewGroup.LayoutParams params = layout.getLayoutParams();
            if(mGridItemWidth !=0){
                params.width = mGridItemWidth;
            }
            layout.setLayoutParams(params);
            return new ItemViewHolder(layout);
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottomsheetdialog_list_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BottomSheetItem bottomSheetItem = sheetItems.get(position);
        if(mode == EMBottomSheetBuilder.MODE_LIST){
            if(holder.getItemViewType() == TYPE_ITEM){
                ((ItemViewHolder)holder).setData((BottomSheetMenuItem) bottomSheetItem);
            }else if(holder.getItemViewType() == TYPE_HEADER){
                ((HeaderViewHolder)holder).setData((BottomSheetHeader) bottomSheetItem);
            }else if(holder.getItemViewType() == TYPE_DIVIDER){
                ((DividerViewHolder)holder).setData((BottomSheetDivider) bottomSheetItem);
            }else{
                throw new IllegalArgumentException("holder.getItemViewType type is not allow");
            }
        }else if(mode == EMBottomSheetBuilder.MODE_GRID){
            ((ItemViewHolder)holder).setData((BottomSheetMenuItem) bottomSheetItem);
        }
    }

    @Override
    public int getItemCount() {
        return sheetItems.size();
    }


    @Override
    public int getItemViewType(int position) {
        BottomSheetItem sheetItem = sheetItems.get(position);
        if(sheetItem instanceof BottomSheetMenuItem){
            return TYPE_ITEM;
        }else if(sheetItem instanceof BottomSheetHeader){
            return TYPE_HEADER;
        }else if(sheetItem instanceof BottomSheetDivider){
            return TYPE_DIVIDER;
        }
        return super.getItemViewType(position);
    }

    /***********************VIEWHOLDER*************************/
    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ItemViewHolder extends ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_item_icon);
            textView = (TextView) itemView.findViewById(R.id.adapter_item_text);
        }

        public void setData(BottomSheetMenuItem menuItem){
            if(menuItem.getmItemIcon() != null){
                imageView.setImageDrawable(menuItem.getmItemIcon());
                imageView.setVisibility(View.VISIBLE);
            }

            textView.setText(menuItem.getText());
            if(menuItem.getmTextColor() != 0){
                textView.setTextColor(menuItem.getmTextColor());
            }

            if(menuItem.getmItemBackground() != 0){
                itemView.setBackgroundColor(menuItem.getmItemBackground());
            }
        }

        @Override
        public void onClick(View v) {
            BottomSheetMenuItem item = (BottomSheetMenuItem) sheetItems.get(getLayoutPosition());
            if(sheetItemClickListener != null){
                sheetItemClickListener.bottomSheetItemClick(item.getmMenuItem());
            }

        }
    }

    class HeaderViewHolder extends ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_item_icon);
            textView = (TextView) itemView.findViewById(R.id.adapter_item_text);
        }

        public void setData(BottomSheetHeader header){
            if(header.getmIcon() != 0){
                imageView.setImageResource(header.getmIcon());
                imageView.setVisibility(View.VISIBLE);
            }

            textView.setText(header.getText());
            if(header.getmTextColor() != 0){
                textView.setTextColor(header.getmTextColor());
            }

            if(header.getmTextBackground() != 0){
                itemView.setBackgroundColor(header.getmTextBackground());
            }
        }
    }

    class DividerViewHolder extends ViewHolder{
        View view;
        public DividerViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.adapter_item_divider);
        }

        public void setData(BottomSheetDivider divider){
            if(divider.getmDividerBackground() != 0){
                view.setBackgroundColor(divider.getmDividerBackground());
            }
        }
    }
}