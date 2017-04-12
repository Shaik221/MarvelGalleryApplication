package gallery.marvel.example.com.marvelgallery.model.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

@SerializedName("offset")
@Expose
private Integer offset;
@SerializedName("limit")
@Expose
private Integer limit;
@SerializedName("total")
@Expose
private Integer total;
@SerializedName("count")
@Expose
private Integer count;
@SerializedName("results")
@Expose
private ArrayList<Result> results = null;

public Integer getOffset() {
return offset;
}

public void setOffset(Integer offset) {
this.offset = offset;
}

public Integer getLimit() {
return limit;
}

public void setLimit(Integer limit) {
this.limit = limit;
}

public Integer getTotal() {
return total;
}

public void setTotal(Integer total) {
this.total = total;
}

public Integer getCount() {
return count;
}

public void setCount(Integer count) {
this.count = count;
}

public ArrayList<Result> getResults() {
return results;
}

public void setResults(ArrayList<Result> results) {
this.results = results;
}

}