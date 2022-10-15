package com.ku.business.entity;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private OrderStatus orderStatus;
    private Date createdAtUtc;
    private Date completedAtUtc;
    private List<Content> contents;

    public Order() {
    }

    public Order(Long id, Date createdAtUtc, Date completedAtUtc, List<Content> contents) {
        this.id = id;
        this.createdAtUtc = createdAtUtc;
        this.completedAtUtc = completedAtUtc;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAtUtc() {
        return createdAtUtc;
    }

    public void setCreatedAtUtc(Date createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }

    public Date getCompletedAtUtc() {
        return completedAtUtc;
    }

    public void setCompletedAtUtc(Date completedAtUtc) {
        this.completedAtUtc = completedAtUtc;
    }

   public OrderStatus getOrderStatus() {
       return orderStatus;
   }

   public void setOrderStatus(OrderStatus orderStatus) {
       this.orderStatus = orderStatus;
   }

    public List<Content> getContentList() {
        return contents;
    }

    public void setContentList(List<Content> contentList) {
        this.contents = contentList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Order aThat = (Order) obj;
        if ((this.id == null && aThat.id != null) || (this.id != null && aThat.id == null)) {return false;}
        if ((this.orderStatus == null && aThat.orderStatus != null) || (this.orderStatus != null && aThat.orderStatus == null)) {return false;}
        if ((this.createdAtUtc == null && aThat.createdAtUtc != null) || (this.createdAtUtc != null && aThat.createdAtUtc == null)) {return false;}
        if ((this.completedAtUtc == null && aThat.completedAtUtc != null) || (this.completedAtUtc != null && aThat.completedAtUtc == null)) {return false;}
        if ((this.contents == null && aThat.contents != null) || (this.contents != null && aThat.contents == null)) {return false;}
        if ((this.contents != null && aThat.contents != null) && (this.contents.size() == aThat.contents.size())) {
            for (int i = 0; i < contents.size(); i++) {
                if (!contents.get(0).getId().equals(aThat.contents.get(0).getId()))
                {return false;}
            }
        }
        return (((this.id == aThat.id) && (aThat.id == null)) || (this.id.equals(aThat.id))) &&
                (((this.orderStatus == aThat.orderStatus) && (aThat.orderStatus == null)) || (this.orderStatus.equals(aThat.orderStatus))) &&
                (((this.createdAtUtc == aThat.createdAtUtc) && (aThat.createdAtUtc == null)) || (this.createdAtUtc.equals(aThat.createdAtUtc))) &&
                (((this.completedAtUtc == aThat.completedAtUtc) && (aThat.completedAtUtc == null)) || (this.completedAtUtc.equals(aThat.completedAtUtc)));
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result +  (id == null ? 0 : id.hashCode());
        result = prime * result + (createdAtUtc == null ? 0 : createdAtUtc.hashCode());
        result = prime * result + (completedAtUtc == null ? 0 : completedAtUtc.hashCode());
        if(contents!=null) {
            for (Content content : contents
            ) {
                result = prime * result + (content!=null && content.getId()!=null ?  (content.getId().hashCode()) : 0);
            }
        }
        return result;
    }
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder(" " + contents.get(0).getClass().getTypeName() + "{ contains");
        for (Content content1 : contents
        ) {
            content.append(" [ detail {" + content1.getId() + "},");
        }
        if (content.length() > 0) {content.setLength(content.length()-1);}
        content.append(" ]");
        return this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", createdAtUtc=" + createdAtUtc +
                " , completedAtUtc=" + completedAtUtc+
                "orderStatus="+ orderStatus +
                "contentList="+ content + "]";
    }
}
