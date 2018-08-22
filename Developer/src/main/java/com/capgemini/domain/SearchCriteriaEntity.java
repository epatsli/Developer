package com.capgemini.domain;

public class SearchCriteriaEntity {

	private Double areaFlatFrom;
	private Double areaFlatTo;

	private Integer numberRoomFrom;
	private Integer numberRoomTo;

	private Integer numberBalconiesFrom;
	private Integer numberBalconiesTo;

	public SearchCriteriaEntity() {
	}

	public SearchCriteriaEntity(SearchCriteriaEntityBuilder builder) {
		this.areaFlatFrom = builder.areaFlatFrom;
		this.areaFlatTo = builder.areaFlatTo;
		this.numberRoomFrom = builder.numberRoomFrom;
		this.numberRoomTo = builder.numberRoomTo;
		this.numberBalconiesFrom = builder.numberBalconiesFrom;
		this.numberBalconiesTo = builder.numberBalconiesTo;
	}

	public static SearchCriteriaEntityBuilder builder() {
		return new SearchCriteriaEntityBuilder();
	}

	public static class SearchCriteriaEntityBuilder {
		private Double areaFlatFrom;
		private Double areaFlatTo;
		private Integer numberRoomFrom;
		private Integer numberRoomTo;
		private Integer numberBalconiesFrom;
		private Integer numberBalconiesTo;

		public SearchCriteriaEntityBuilder withAreaFlatFrom(Double areaFlatFrom) {
			this.areaFlatFrom = areaFlatFrom;
			return this;
		}

		public SearchCriteriaEntityBuilder withAreaFlatTo(Double areaFlatTo) {
			this.areaFlatTo = areaFlatTo;
			return this;
		}

		public SearchCriteriaEntityBuilder withNumberRoomFrom(Integer numberRoomFrom) {
			this.numberRoomFrom = numberRoomFrom;
			return this;
		}

		public SearchCriteriaEntityBuilder withNumberRoomTo(Integer numberRoomTo) {
			this.numberRoomTo = numberRoomTo;
			return this;
		}

		public SearchCriteriaEntityBuilder withNumberBalconiesFrom(Integer numberBalconiesFrom) {
			this.numberBalconiesFrom = numberBalconiesFrom;
			return this;
		}

		public SearchCriteriaEntityBuilder withNumberBalconiesTo(Integer numberBalconiesTo) {
			this.numberBalconiesTo = numberBalconiesTo;
			return this;
		}

		public SearchCriteriaEntity build() {
			return new SearchCriteriaEntity(this);

		}

	}

}
