package com.andreiverdes.training.expleo.stackoverflow.model;

import java.util.Objects;

public class DbQuestionTag {
        public int questionId;
        public String value;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                DbQuestionTag that = (DbQuestionTag) o;
                return questionId == that.questionId &&
                        Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
                return Objects.hash(questionId, value);
        }
}
