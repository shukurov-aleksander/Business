package com.ku.business.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime createdAtUtc;
    private LocalDateTime completedAtUtc;
    private List<Content> contents;

    public Order() {
    }

    public Order(Long id, LocalDateTime createdAtUtc, LocalDateTime completedAtUtc, List<Content> contents) {
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

    public LocalDateTime getCreatedAtUtc() {
        return createdAtUtc;
    }

    public void setCreatedAtUtc(LocalDateTime createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }

    public LocalDateTime getCompletedAtUtc() {
        return completedAtUtc;
    }

    public void setCompletedAtUtc(LocalDateTime completedAtUtc) {
        this.completedAtUtc = completedAtUtc;
    }

   public OrderStatus getOrderStatus() {
       return orderStatus;
   }

   public void setOrderStatus(OrderStatus orderStatus) {
       this.orderStatus = orderStatus;
   }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contentList) {
        this.contents = contentList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Order aThat = (Order) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getCreatedAtUtc() == null) {
            if (aThat.getCreatedAtUtc() != null) {return false;}
        } else if (!getCreatedAtUtc().equals(aThat.getCreatedAtUtc())) {return false;}

        if (getCompletedAtUtc() == null) {
            if (aThat.getCompletedAtUtc() != null) {return false;}
        } else if (!getCompletedAtUtc().equals(aThat.getCompletedAtUtc())) {return false;}

        if ((getContents() == null && aThat.getContents() != null) || (getContents() != null && aThat.getContents() == null)) {return false;}
        else if (getContents() != null && aThat.getContents() != null) {
            for (int i = 0; i < getContents().size(); i++) {
                if (!getContents().get(i).getId().equals(aThat.getContents().get(i).getId())) {return false;}
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result +  (id == null ? 0 : id.hashCode());
        result = prime * result + (createdAtUtc == null ? 0 : createdAtUtc.hashCode());
        result = prime * result + (completedAtUtc == null ? 0 : completedAtUtc.hashCode());
        if(contents != null) {
            for (Content content : contents) {
                result = prime * result + (content != null && content.getId() != null ?  (content.getId().hashCode()) : 0);
            }
        }
        return result;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" {id=").append(getId())
                .append(", createdAtUtc=").append(getCreatedAtUtc())
                .append(", completedAtUtc=").append(getCompletedAtUtc())
                .append(", Content contains [");
        if (getContents() != null && !getContents().isEmpty()) {
            for (Content content: contents) {
                stringBuilder.append("detail {").append(content.getId()).append("}, ");
            }
            stringBuilder.setLength(stringBuilder.length()-2);
        }
                stringBuilder.append("]}");
        return stringBuilder.toString();
    }
}
