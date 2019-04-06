package iamutkarshtiwari.github.io.ananas.editimage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import iamutkarshtiwari.github.io.ananas.R;
import iamutkarshtiwari.github.io.ananas.editimage.fragment.StickerFragment;


public class StickerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private StickerFragment mStickerFragment;
    private ImageClick mImageClick = new ImageClick();
    private List<String> pathList = new ArrayList<String>();

    public StickerAdapter(StickerFragment fragment) {
        super();
        this.mStickerFragment = fragment;
    }

    public class ImageHolder extends ViewHolder {
        public ImageView image;

        ImageHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View v = null;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_sticker_item, parent, false);
        ImageHolder holder = new ImageHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageHolder imageHolder = (ImageHolder) holder;
        String path = pathList.get(position);

        String imageUrl = "drawable/" + path;
        int imageKey = mStickerFragment.getResources().getIdentifier(imageUrl, "drawable", mStickerFragment.getContext().getPackageName());
        imageHolder.image.setImageDrawable(mStickerFragment.getResources().getDrawable(imageKey));
        imageHolder.image.setTag(imageUrl);
        imageHolder.image.setOnClickListener(mImageClick);
    }

    public void addStickerImages(String folderPath, int stickerCount) {
        pathList.clear();
        for (int i = 0; i < stickerCount; i++) {
            pathList.add(folderPath + "_" + Integer.toString(i+1));
        }
        this.notifyDataSetChanged();
    }

    private final class ImageClick implements OnClickListener {
        @Override
        public void onClick(View v) {
            String data = (String) v.getTag();
            mStickerFragment.selectedStickerItem(data);
        }
    }
}
