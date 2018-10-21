package israelfl.com.dejsontest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView Adapter for a custom view based on the {@link Book} model.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private LayoutInflater inflater;
    private List<Book> mBookList = Collections.emptyList();
    private View view;
    private Context mContext;

    public BookAdapter(Context context, List<Book> bookList) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        this.mBookList = bookList;
    }

    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.item_book, parent, false);
        return new BookAdapter.BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookAdapter.BookViewHolder holder, int position) {
        Book current = mBookList.get(position);

        // Ask Picasso to populate the view from the given URL
        Picasso.with(mContext).load(current.getBoxArt()).into(holder.ivBook);
        holder.tvTitle.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivBook;
        private TextView tvTitle;

        public BookViewHolder(View view) {
            super(view);
            ivBook = view.findViewById(R.id.iv_book);
            tvTitle = view.findViewById(R.id.tv_title);
        }

    }

}