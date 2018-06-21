package com.flyer.domain;

import java.util.ArrayList;
import java.util.List;

public class CustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerExample() {
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

        public Criteria andFIdIsNull() {
            addCriterion("f_id is null");
            return (Criteria) this;
        }

        public Criteria andFIdIsNotNull() {
            addCriterion("f_id is not null");
            return (Criteria) this;
        }

        public Criteria andFIdEqualTo(Integer value) {
            addCriterion("f_id =", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdNotEqualTo(Integer value) {
            addCriterion("f_id <>", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdGreaterThan(Integer value) {
            addCriterion("f_id >", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_id >=", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdLessThan(Integer value) {
            addCriterion("f_id <", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdLessThanOrEqualTo(Integer value) {
            addCriterion("f_id <=", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdIn(List<Integer> values) {
            addCriterion("f_id in", values, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdNotIn(List<Integer> values) {
            addCriterion("f_id not in", values, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdBetween(Integer value1, Integer value2) {
            addCriterion("f_id between", value1, value2, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdNotBetween(Integer value1, Integer value2) {
            addCriterion("f_id not between", value1, value2, "fId");
            return (Criteria) this;
        }

        public Criteria andFNameIsNull() {
            addCriterion("f_name is null");
            return (Criteria) this;
        }

        public Criteria andFNameIsNotNull() {
            addCriterion("f_name is not null");
            return (Criteria) this;
        }

        public Criteria andFNameEqualTo(String value) {
            addCriterion("f_name =", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotEqualTo(String value) {
            addCriterion("f_name <>", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameGreaterThan(String value) {
            addCriterion("f_name >", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameGreaterThanOrEqualTo(String value) {
            addCriterion("f_name >=", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLessThan(String value) {
            addCriterion("f_name <", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLessThanOrEqualTo(String value) {
            addCriterion("f_name <=", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLike(String value) {
            addCriterion("f_name like", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotLike(String value) {
            addCriterion("f_name not like", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameIn(List<String> values) {
            addCriterion("f_name in", values, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotIn(List<String> values) {
            addCriterion("f_name not in", values, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameBetween(String value1, String value2) {
            addCriterion("f_name between", value1, value2, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotBetween(String value1, String value2) {
            addCriterion("f_name not between", value1, value2, "fName");
            return (Criteria) this;
        }

        public Criteria andFPhoneIsNull() {
            addCriterion("f_phone is null");
            return (Criteria) this;
        }

        public Criteria andFPhoneIsNotNull() {
            addCriterion("f_phone is not null");
            return (Criteria) this;
        }

        public Criteria andFPhoneEqualTo(String value) {
            addCriterion("f_phone =", value, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneNotEqualTo(String value) {
            addCriterion("f_phone <>", value, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneGreaterThan(String value) {
            addCriterion("f_phone >", value, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("f_phone >=", value, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneLessThan(String value) {
            addCriterion("f_phone <", value, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneLessThanOrEqualTo(String value) {
            addCriterion("f_phone <=", value, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneLike(String value) {
            addCriterion("f_phone like", value, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneNotLike(String value) {
            addCriterion("f_phone not like", value, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneIn(List<String> values) {
            addCriterion("f_phone in", values, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneNotIn(List<String> values) {
            addCriterion("f_phone not in", values, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneBetween(String value1, String value2) {
            addCriterion("f_phone between", value1, value2, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFPhoneNotBetween(String value1, String value2) {
            addCriterion("f_phone not between", value1, value2, "fPhone");
            return (Criteria) this;
        }

        public Criteria andFSexIsNull() {
            addCriterion("f_sex is null");
            return (Criteria) this;
        }

        public Criteria andFSexIsNotNull() {
            addCriterion("f_sex is not null");
            return (Criteria) this;
        }

        public Criteria andFSexEqualTo(String value) {
            addCriterion("f_sex =", value, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexNotEqualTo(String value) {
            addCriterion("f_sex <>", value, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexGreaterThan(String value) {
            addCriterion("f_sex >", value, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexGreaterThanOrEqualTo(String value) {
            addCriterion("f_sex >=", value, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexLessThan(String value) {
            addCriterion("f_sex <", value, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexLessThanOrEqualTo(String value) {
            addCriterion("f_sex <=", value, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexLike(String value) {
            addCriterion("f_sex like", value, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexNotLike(String value) {
            addCriterion("f_sex not like", value, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexIn(List<String> values) {
            addCriterion("f_sex in", values, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexNotIn(List<String> values) {
            addCriterion("f_sex not in", values, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexBetween(String value1, String value2) {
            addCriterion("f_sex between", value1, value2, "fSex");
            return (Criteria) this;
        }

        public Criteria andFSexNotBetween(String value1, String value2) {
            addCriterion("f_sex not between", value1, value2, "fSex");
            return (Criteria) this;
        }

        public Criteria andFCardIdIsNull() {
            addCriterion("f_card_id is null");
            return (Criteria) this;
        }

        public Criteria andFCardIdIsNotNull() {
            addCriterion("f_card_id is not null");
            return (Criteria) this;
        }

        public Criteria andFCardIdEqualTo(String value) {
            addCriterion("f_card_id =", value, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdNotEqualTo(String value) {
            addCriterion("f_card_id <>", value, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdGreaterThan(String value) {
            addCriterion("f_card_id >", value, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("f_card_id >=", value, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdLessThan(String value) {
            addCriterion("f_card_id <", value, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdLessThanOrEqualTo(String value) {
            addCriterion("f_card_id <=", value, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdLike(String value) {
            addCriterion("f_card_id like", value, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdNotLike(String value) {
            addCriterion("f_card_id not like", value, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdIn(List<String> values) {
            addCriterion("f_card_id in", values, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdNotIn(List<String> values) {
            addCriterion("f_card_id not in", values, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdBetween(String value1, String value2) {
            addCriterion("f_card_id between", value1, value2, "fCardId");
            return (Criteria) this;
        }

        public Criteria andFCardIdNotBetween(String value1, String value2) {
            addCriterion("f_card_id not between", value1, value2, "fCardId");
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