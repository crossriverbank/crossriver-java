package io.finix.payments.lib;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

public class Page<T> implements View {

  public List<T> contents;

  @Getter @Setter
  Class<T> type;

  @Getter @Setter
  Integer total;

  @Setter
  PageInfo page;

  @Setter
  @JsonProperty("_links")
  Links links;

  @Setter
  @Getter
  @JsonProperty("_embedded")
  public Embedded embedded;

  @Setter
  HttpClient httpClient;

  public Page<T> getNext() {
    return get(links.next.href);
  }

  public Page<T> getPrevious() {
    return get(links.prev.href);
  }

  public Page<T> getFirst() {
    return get(links.first.href);
  }

  public Page<T> getLast() {
    return get(links.last.href);
  }

  public Boolean hasNext() {
    return (links.next != null);
  }

  public Boolean hasPrevious() {
    return (links.prev != null);
  }

  public Boolean hasFirst() {
    return (links.first != null);
  }

  public Boolean hasLast() {
    return (links.last != null);
  }

  public Page<T> get(String rel) {
    try {
      String json = httpClient.getUrl(rel).body().string();
      Page<T> page = JsonMapper.parsePage(json, (Class) this.getType(), httpClient);
      return page;
    } catch (IOException e) {
      return null;
    }
  }

  @Getter
  public class Embedded<T> {
    String name;
    public List<T> items;

    @JsonAnySetter
    private void setList(String name, List<T> list) {
      this.items = list;
      this.name = name;
    }
  }

  public Integer getCount() {
    return this.page.count;
  }

  public Integer getLimit() {
    return this.page.limit;
  }

  public Integer getOffset() {
    return this.page.offset;
  }

}

class PageInfo {
  public Integer count;
  public Integer limit;
  public Integer offset;
}

class Links {
  public Link first;
  public Link last;
  public Link prev;
  public Link next;
  public Link self;
}

class Link {
  public String href;
}
