package cn.itheima.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRidIsNull() {
            addCriterion("rid is null");
            return (Criteria) this;
        }

        public Criteria andRidIsNotNull() {
            addCriterion("rid is not null");
            return (Criteria) this;
        }

        public Criteria andRidEqualTo(Integer value) {
            addCriterion("rid =", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotEqualTo(Integer value) {
            addCriterion("rid <>", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThan(Integer value) {
            addCriterion("rid >", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThanOrEqualTo(Integer value) {
            addCriterion("rid >=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThan(Integer value) {
            addCriterion("rid <", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThanOrEqualTo(Integer value) {
            addCriterion("rid <=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidIn(List<Integer> values) {
            addCriterion("rid in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotIn(List<Integer> values) {
            addCriterion("rid not in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidBetween(Integer value1, Integer value2) {
            addCriterion("rid between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotBetween(Integer value1, Integer value2) {
            addCriterion("rid not between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andStaIsNull() {
            addCriterion("sta is null");
            return (Criteria) this;
        }

        public Criteria andStaIsNotNull() {
            addCriterion("sta is not null");
            return (Criteria) this;
        }

        public Criteria andStaEqualTo(String value) {
            addCriterion("sta =", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaNotEqualTo(String value) {
            addCriterion("sta <>", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaGreaterThan(String value) {
            addCriterion("sta >", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaGreaterThanOrEqualTo(String value) {
            addCriterion("sta >=", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaLessThan(String value) {
            addCriterion("sta <", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaLessThanOrEqualTo(String value) {
            addCriterion("sta <=", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaLike(String value) {
            addCriterion("sta like", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaNotLike(String value) {
            addCriterion("sta not like", value, "sta");
            return (Criteria) this;
        }

        public Criteria andStaIn(List<String> values) {
            addCriterion("sta in", values, "sta");
            return (Criteria) this;
        }

        public Criteria andStaNotIn(List<String> values) {
            addCriterion("sta not in", values, "sta");
            return (Criteria) this;
        }

        public Criteria andStaBetween(String value1, String value2) {
            addCriterion("sta between", value1, value2, "sta");
            return (Criteria) this;
        }

        public Criteria andStaNotBetween(String value1, String value2) {
            addCriterion("sta not between", value1, value2, "sta");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNull() {
            addCriterion("payment is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNotNull() {
            addCriterion("payment is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEqualTo(Double value) {
            addCriterion("payment =", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotEqualTo(Double value) {
            addCriterion("payment <>", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThan(Double value) {
            addCriterion("payment >", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThanOrEqualTo(Double value) {
            addCriterion("payment >=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThan(Double value) {
            addCriterion("payment <", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThanOrEqualTo(Double value) {
            addCriterion("payment <=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentIn(List<Double> values) {
            addCriterion("payment in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotIn(List<Double> values) {
            addCriterion("payment not in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentBetween(Double value1, Double value2) {
            addCriterion("payment between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotBetween(Double value1, Double value2) {
            addCriterion("payment not between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPlacedIsNull() {
            addCriterion("placed is null");
            return (Criteria) this;
        }

        public Criteria andPlacedIsNotNull() {
            addCriterion("placed is not null");
            return (Criteria) this;
        }

        public Criteria andPlacedEqualTo(Date value) {
            addCriterion("placed =", value, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedNotEqualTo(Date value) {
            addCriterion("placed <>", value, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedGreaterThan(Date value) {
            addCriterion("placed >", value, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedGreaterThanOrEqualTo(Date value) {
            addCriterion("placed >=", value, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedLessThan(Date value) {
            addCriterion("placed <", value, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedLessThanOrEqualTo(Date value) {
            addCriterion("placed <=", value, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedIn(List<Date> values) {
            addCriterion("placed in", values, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedNotIn(List<Date> values) {
            addCriterion("placed not in", values, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedBetween(Date value1, Date value2) {
            addCriterion("placed between", value1, value2, "placed");
            return (Criteria) this;
        }

        public Criteria andPlacedNotBetween(Date value1, Date value2) {
            addCriterion("placed not between", value1, value2, "placed");
            return (Criteria) this;
        }

        public Criteria andReceiptIsNull() {
            addCriterion("receipt is null");
            return (Criteria) this;
        }

        public Criteria andReceiptIsNotNull() {
            addCriterion("receipt is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptEqualTo(Date value) {
            addCriterion("receipt =", value, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptNotEqualTo(Date value) {
            addCriterion("receipt <>", value, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptGreaterThan(Date value) {
            addCriterion("receipt >", value, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptGreaterThanOrEqualTo(Date value) {
            addCriterion("receipt >=", value, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptLessThan(Date value) {
            addCriterion("receipt <", value, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptLessThanOrEqualTo(Date value) {
            addCriterion("receipt <=", value, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptIn(List<Date> values) {
            addCriterion("receipt in", values, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptNotIn(List<Date> values) {
            addCriterion("receipt not in", values, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptBetween(Date value1, Date value2) {
            addCriterion("receipt between", value1, value2, "receipt");
            return (Criteria) this;
        }

        public Criteria andReceiptNotBetween(Date value1, Date value2) {
            addCriterion("receipt not between", value1, value2, "receipt");
            return (Criteria) this;
        }

        public Criteria andDeliverIsNull() {
            addCriterion("deliver is null");
            return (Criteria) this;
        }

        public Criteria andDeliverIsNotNull() {
            addCriterion("deliver is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverEqualTo(Date value) {
            addCriterion("deliver =", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverNotEqualTo(Date value) {
            addCriterion("deliver <>", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverGreaterThan(Date value) {
            addCriterion("deliver >", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverGreaterThanOrEqualTo(Date value) {
            addCriterion("deliver >=", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverLessThan(Date value) {
            addCriterion("deliver <", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverLessThanOrEqualTo(Date value) {
            addCriterion("deliver <=", value, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverIn(List<Date> values) {
            addCriterion("deliver in", values, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverNotIn(List<Date> values) {
            addCriterion("deliver not in", values, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverBetween(Date value1, Date value2) {
            addCriterion("deliver between", value1, value2, "deliver");
            return (Criteria) this;
        }

        public Criteria andDeliverNotBetween(Date value1, Date value2) {
            addCriterion("deliver not between", value1, value2, "deliver");
            return (Criteria) this;
        }

        public Criteria andHandoverIsNull() {
            addCriterion("handover is null");
            return (Criteria) this;
        }

        public Criteria andHandoverIsNotNull() {
            addCriterion("handover is not null");
            return (Criteria) this;
        }

        public Criteria andHandoverEqualTo(Date value) {
            addCriterion("handover =", value, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverNotEqualTo(Date value) {
            addCriterion("handover <>", value, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverGreaterThan(Date value) {
            addCriterion("handover >", value, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverGreaterThanOrEqualTo(Date value) {
            addCriterion("handover >=", value, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverLessThan(Date value) {
            addCriterion("handover <", value, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverLessThanOrEqualTo(Date value) {
            addCriterion("handover <=", value, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverIn(List<Date> values) {
            addCriterion("handover in", values, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverNotIn(List<Date> values) {
            addCriterion("handover not in", values, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverBetween(Date value1, Date value2) {
            addCriterion("handover between", value1, value2, "handover");
            return (Criteria) this;
        }

        public Criteria andHandoverNotBetween(Date value1, Date value2) {
            addCriterion("handover not between", value1, value2, "handover");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}