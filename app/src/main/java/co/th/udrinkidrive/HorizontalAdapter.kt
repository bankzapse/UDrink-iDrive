package co.th.udrinkidrive

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HorizontalAdapter(private val titles: Array<String>, private val activity: Activity) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: HorizontalAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_muti_snap_listview, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: HorizontalAdapter.ViewHolder, position: Int) {
    //SetOnClick
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView? = null
//        val lv_box_shop: LinearLayout

        init {
            //            this.title = (TextView) itemView.findViewById(R.id.title);
//            this.lv_box_shop = itemView.findViewById<View>(R.id.lv_box_shop) as LinearLayout
            //            this.lv_box_shop.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View view) {
            //                    Log.d("Tag","onClick");
            //                }
            //            });
        }
    }

}