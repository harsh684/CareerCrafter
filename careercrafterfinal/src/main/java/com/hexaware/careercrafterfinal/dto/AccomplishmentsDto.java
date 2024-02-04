package com.hexaware.careercrafterfinal.dto;

public class AccomplishmentsDto {

		private long accomplishmentId;
		
		private String description;
		
		public AccomplishmentsDto() {
			super();
		}
		
		public AccomplishmentsDto(long accomplishmentId, String description) {
			super();
			this.accomplishmentId = accomplishmentId;
			this.description = description;
		}
		public long getAccomplishmentId() {
			return accomplishmentId;
		}
		public void setAccomplishmentId(long accomplishmentId) {
			this.accomplishmentId = accomplishmentId;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		@Override
		public String toString() {
			return "Accomplishments [accomplishmentId=" + accomplishmentId + ", description=" + description + "]";
		}
}
