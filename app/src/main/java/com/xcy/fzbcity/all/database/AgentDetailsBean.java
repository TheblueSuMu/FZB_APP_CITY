package com.xcy.fzbcity.all.database;

import java.util.List;

public class 	AgentDetailsBean {

	/**
	 * code : 1
	 * msg : 成功
	 * data : {"agentDataStatistics":{"accessingNumber":0,"tradeNumber":3,"reportNumber":3,"isIslandNumber":1,"earnestMoneyNumber":2,"landingNumber":0,"InvalidNum":0},"agentInfo":{"id":"b50bf8ff03384c108983ee34f2622959","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"xs2","phone":"18235195902","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"2019-09-27 17:50:42","loginFlag":"1","type":"2","searcName":"","parentId":"ce73555ff4764cf6917a17ba9e7f228f","parentIds":"ce73555ff4764cf6917a17ba9e7f228f","user":"","leaderName":"张冰","saleName":"","saleNum":"","counselorNum":"","ratioName":"销售(三级)"},"gsonOption":{"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["21","22","23","24","25","26"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]},"agentMoneyData":{"totalAmount":"0","notAmount":"0","alreadyAmount":"0"}}
	 */

	private String code;
	private String msg;
	private DataBean data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * agentDataStatistics : {"accessingNumber":0,"tradeNumber":3,"reportNumber":3,"isIslandNumber":1,"earnestMoneyNumber":2,"landingNumber":0,"InvalidNum":0}
		 * agentInfo : {"id":"b50bf8ff03384c108983ee34f2622959","remarks":"","createBy":"","createDate":"","updateBy":"","updateDate":"","name":"xs2","phone":"18235195902","photo":"/fangfang/static/common/images/flat-avatar.png","identity":"61","loginDate":"2019-09-27 17:50:42","loginFlag":"1","type":"2","searcName":"","parentId":"ce73555ff4764cf6917a17ba9e7f228f","parentIds":"ce73555ff4764cf6917a17ba9e7f228f","user":"","leaderName":"张冰","saleName":"","saleNum":"","counselorNum":"","ratioName":"销售(三级)"}
		 * gsonOption : {"yAxis":{"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"},"xAxis":{"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["21","22","23","24","25","26"],"type":"category","boundaryGap":false},"series":[{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]}
		 * agentMoneyData : {"totalAmount":"0","notAmount":"0","alreadyAmount":"0"}
		 */

		private AgentDataStatisticsBean agentDataStatistics;
		private AgentInfoBean agentInfo;
		private GsonOptionBean gsonOption;
		private AgentMoneyDataBean agentMoneyData;

		public AgentDataStatisticsBean getAgentDataStatistics() {
			return agentDataStatistics;
		}

		public void setAgentDataStatistics(AgentDataStatisticsBean agentDataStatistics) {
			this.agentDataStatistics = agentDataStatistics;
		}

		public AgentInfoBean getAgentInfo() {
			return agentInfo;
		}

		public void setAgentInfo(AgentInfoBean agentInfo) {
			this.agentInfo = agentInfo;
		}

		public GsonOptionBean getGsonOption() {
			return gsonOption;
		}

		public void setGsonOption(GsonOptionBean gsonOption) {
			this.gsonOption = gsonOption;
		}

		public AgentMoneyDataBean getAgentMoneyData() {
			return agentMoneyData;
		}

		public void setAgentMoneyData(AgentMoneyDataBean agentMoneyData) {
			this.agentMoneyData = agentMoneyData;
		}

		public static class AgentDataStatisticsBean {
			/**
			 * accessingNumber : 0
			 * tradeNumber : 3
			 * reportNumber : 3
			 * isIslandNumber : 1
			 * earnestMoneyNumber : 2
			 * landingNumber : 0
			 * InvalidNum : 0
			 */

			private int accessingNumber;
			private int tradeNumber;
			private int reportNumber;
			private int isIslandNumber;
			private int earnestMoneyNumber;
			private int landingNumber;
			private int InvalidNum;

			public int getAccessingNumber() {
				return accessingNumber;
			}

			public void setAccessingNumber(int accessingNumber) {
				this.accessingNumber = accessingNumber;
			}

			public int getTradeNumber() {
				return tradeNumber;
			}

			public void setTradeNumber(int tradeNumber) {
				this.tradeNumber = tradeNumber;
			}

			public int getReportNumber() {
				return reportNumber;
			}

			public void setReportNumber(int reportNumber) {
				this.reportNumber = reportNumber;
			}

			public int getIsIslandNumber() {
				return isIslandNumber;
			}

			public void setIsIslandNumber(int isIslandNumber) {
				this.isIslandNumber = isIslandNumber;
			}

			public int getEarnestMoneyNumber() {
				return earnestMoneyNumber;
			}

			public void setEarnestMoneyNumber(int earnestMoneyNumber) {
				this.earnestMoneyNumber = earnestMoneyNumber;
			}

			public int getLandingNumber() {
				return landingNumber;
			}

			public void setLandingNumber(int landingNumber) {
				this.landingNumber = landingNumber;
			}

			public int getInvalidNum() {
				return InvalidNum;
			}

			public void setInvalidNum(int InvalidNum) {
				this.InvalidNum = InvalidNum;
			}
		}

		public static class AgentInfoBean {
			/**
			 * id : b50bf8ff03384c108983ee34f2622959
			 * remarks :
			 * createBy :
			 * createDate :
			 * updateBy :
			 * updateDate :
			 * name : xs2
			 * phone : 18235195902
			 * photo : /fangfang/static/common/images/flat-avatar.png
			 * identity : 61
			 * loginDate : 2019-09-27 17:50:42
			 * loginFlag : 1
			 * type : 2
			 * searcName :
			 * parentId : ce73555ff4764cf6917a17ba9e7f228f
			 * parentIds : ce73555ff4764cf6917a17ba9e7f228f
			 * user :
			 * leaderName : 张冰
			 * saleName :
			 * saleNum :
			 * counselorNum :
			 * ratioName : 销售(三级)
			 */

			private String id;
			private String remarks;
			private String createBy;
			private String createDate;
			private String updateBy;
			private String updateDate;
			private String name;
			private String phone;
			private String photo;
			private String identity;
			private String loginDate;
			private String loginFlag;
			private String type;
			private String searcName;
			private String parentId;
			private String parentIds;
			private String user;
			private String leaderName;
			private String saleName;
			private String saleNum;
			private String counselorNum;
			private String ratioName;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getRemarks() {
				return remarks;
			}

			public void setRemarks(String remarks) {
				this.remarks = remarks;
			}

			public String getCreateBy() {
				return createBy;
			}

			public void setCreateBy(String createBy) {
				this.createBy = createBy;
			}

			public String getCreateDate() {
				return createDate;
			}

			public void setCreateDate(String createDate) {
				this.createDate = createDate;
			}

			public String getUpdateBy() {
				return updateBy;
			}

			public void setUpdateBy(String updateBy) {
				this.updateBy = updateBy;
			}

			public String getUpdateDate() {
				return updateDate;
			}

			public void setUpdateDate(String updateDate) {
				this.updateDate = updateDate;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getPhone() {
				return phone;
			}

			public void setPhone(String phone) {
				this.phone = phone;
			}

			public String getPhoto() {
				return photo;
			}

			public void setPhoto(String photo) {
				this.photo = photo;
			}

			public String getIdentity() {
				return identity;
			}

			public void setIdentity(String identity) {
				this.identity = identity;
			}

			public String getLoginDate() {
				return loginDate;
			}

			public void setLoginDate(String loginDate) {
				this.loginDate = loginDate;
			}

			public String getLoginFlag() {
				return loginFlag;
			}

			public void setLoginFlag(String loginFlag) {
				this.loginFlag = loginFlag;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getSearcName() {
				return searcName;
			}

			public void setSearcName(String searcName) {
				this.searcName = searcName;
			}

			public String getParentId() {
				return parentId;
			}

			public void setParentId(String parentId) {
				this.parentId = parentId;
			}

			public String getParentIds() {
				return parentIds;
			}

			public void setParentIds(String parentIds) {
				this.parentIds = parentIds;
			}

			public String getUser() {
				return user;
			}

			public void setUser(String user) {
				this.user = user;
			}

			public String getLeaderName() {
				return leaderName;
			}

			public void setLeaderName(String leaderName) {
				this.leaderName = leaderName;
			}

			public String getSaleName() {
				return saleName;
			}

			public void setSaleName(String saleName) {
				this.saleName = saleName;
			}

			public String getSaleNum() {
				return saleNum;
			}

			public void setSaleNum(String saleNum) {
				this.saleNum = saleNum;
			}

			public String getCounselorNum() {
				return counselorNum;
			}

			public void setCounselorNum(String counselorNum) {
				this.counselorNum = counselorNum;
			}

			public String getRatioName() {
				return ratioName;
			}

			public void setRatioName(String ratioName) {
				this.ratioName = ratioName;
			}
		}

		public static class GsonOptionBean {
			/**
			 * yAxis : {"axisLabel":{"show":true,"textStyle":{"color":"#a8b6cc"}},"splitLine":{"lineStyle":{"color":"#eeeeee"},"show":true},"type":"value"}
			 * xAxis : {"axisLabel":{"textStyle":{"color":"#a8b6cc"}},"data":["21","22","23","24","25","26"],"type":"category","boundaryGap":false}
			 * series : [{"areaStyle":{"color":"#e6eefe"},"data":[0,0,0,0,0,0],"itemStyle":{"color":"#3b82d2"},"type":"line"}]
			 */

			private YAxisBean yAxis;
			private XAxisBean xAxis;
			private List<SeriesBean> series;

			public YAxisBean getYAxis() {
				return yAxis;
			}

			public void setYAxis(YAxisBean yAxis) {
				this.yAxis = yAxis;
			}

			public XAxisBean getXAxis() {
				return xAxis;
			}

			public void setXAxis(XAxisBean xAxis) {
				this.xAxis = xAxis;
			}

			public List<SeriesBean> getSeries() {
				return series;
			}

			public void setSeries(List<SeriesBean> series) {
				this.series = series;
			}

			public static class YAxisBean {
				/**
				 * axisLabel : {"show":true,"textStyle":{"color":"#a8b6cc"}}
				 * splitLine : {"lineStyle":{"color":"#eeeeee"},"show":true}
				 * type : value
				 */

				private AxisLabelBean axisLabel;
				private SplitLineBean splitLine;
				private String type;

				public AxisLabelBean getAxisLabel() {
					return axisLabel;
				}

				public void setAxisLabel(AxisLabelBean axisLabel) {
					this.axisLabel = axisLabel;
				}

				public SplitLineBean getSplitLine() {
					return splitLine;
				}

				public void setSplitLine(SplitLineBean splitLine) {
					this.splitLine = splitLine;
				}

				public String getType() {
					return type;
				}

				public void setType(String type) {
					this.type = type;
				}

				public static class AxisLabelBean {
					/**
					 * show : true
					 * textStyle : {"color":"#a8b6cc"}
					 */

					private boolean show;
					private TextStyleBean textStyle;

					public boolean isShow() {
						return show;
					}

					public void setShow(boolean show) {
						this.show = show;
					}

					public TextStyleBean getTextStyle() {
						return textStyle;
					}

					public void setTextStyle(TextStyleBean textStyle) {
						this.textStyle = textStyle;
					}

					public static class TextStyleBean {
						/**
						 * color : #a8b6cc
						 */

						private String color;

						public String getColor() {
							return color;
						}

						public void setColor(String color) {
							this.color = color;
						}
					}
				}

				public static class SplitLineBean {
					/**
					 * lineStyle : {"color":"#eeeeee"}
					 * show : true
					 */

					private LineStyleBean lineStyle;
					private boolean show;

					public LineStyleBean getLineStyle() {
						return lineStyle;
					}

					public void setLineStyle(LineStyleBean lineStyle) {
						this.lineStyle = lineStyle;
					}

					public boolean isShow() {
						return show;
					}

					public void setShow(boolean show) {
						this.show = show;
					}

					public static class LineStyleBean {
						/**
						 * color : #eeeeee
						 */

						private String color;

						public String getColor() {
							return color;
						}

						public void setColor(String color) {
							this.color = color;
						}
					}
				}
			}

			public static class XAxisBean {
				/**
				 * axisLabel : {"textStyle":{"color":"#a8b6cc"}}
				 * data : ["21","22","23","24","25","26"]
				 * type : category
				 * boundaryGap : false
				 */

				private AxisLabelBeanX axisLabel;
				private String type;
				private boolean boundaryGap;
				private List<String> data;

				public AxisLabelBeanX getAxisLabel() {
					return axisLabel;
				}

				public void setAxisLabel(AxisLabelBeanX axisLabel) {
					this.axisLabel = axisLabel;
				}

				public String getType() {
					return type;
				}

				public void setType(String type) {
					this.type = type;
				}

				public boolean isBoundaryGap() {
					return boundaryGap;
				}

				public void setBoundaryGap(boolean boundaryGap) {
					this.boundaryGap = boundaryGap;
				}

				public List<String> getData() {
					return data;
				}

				public void setData(List<String> data) {
					this.data = data;
				}

				public static class AxisLabelBeanX {
					/**
					 * textStyle : {"color":"#a8b6cc"}
					 */

					private TextStyleBeanX textStyle;

					public TextStyleBeanX getTextStyle() {
						return textStyle;
					}

					public void setTextStyle(TextStyleBeanX textStyle) {
						this.textStyle = textStyle;
					}

					public static class TextStyleBeanX {
						/**
						 * color : #a8b6cc
						 */

						private String color;

						public String getColor() {
							return color;
						}

						public void setColor(String color) {
							this.color = color;
						}
					}
				}
			}

			public static class SeriesBean {
				/**
				 * areaStyle : {"color":"#e6eefe"}
				 * data : [0,0,0,0,0,0]
				 * itemStyle : {"color":"#3b82d2"}
				 * type : line
				 */

				private AreaStyleBean areaStyle;
				private ItemStyleBean itemStyle;
				private String type;
				private List<Integer> data;

				public AreaStyleBean getAreaStyle() {
					return areaStyle;
				}

				public void setAreaStyle(AreaStyleBean areaStyle) {
					this.areaStyle = areaStyle;
				}

				public ItemStyleBean getItemStyle() {
					return itemStyle;
				}

				public void setItemStyle(ItemStyleBean itemStyle) {
					this.itemStyle = itemStyle;
				}

				public String getType() {
					return type;
				}

				public void setType(String type) {
					this.type = type;
				}

				public List<Integer> getData() {
					return data;
				}

				public void setData(List<Integer> data) {
					this.data = data;
				}

				public static class AreaStyleBean {
					/**
					 * color : #e6eefe
					 */

					private String color;

					public String getColor() {
						return color;
					}

					public void setColor(String color) {
						this.color = color;
					}
				}

				public static class ItemStyleBean {
					/**
					 * color : #3b82d2
					 */

					private String color;

					public String getColor() {
						return color;
					}

					public void setColor(String color) {
						this.color = color;
					}
				}
			}
		}

		public static class AgentMoneyDataBean {
			/**
			 * totalAmount : 0
			 * notAmount : 0
			 * alreadyAmount : 0
			 */

			private String totalAmount;
			private String notAmount;
			private String alreadyAmount;

			public String getTotalAmount() {
				return totalAmount;
			}

			public void setTotalAmount(String totalAmount) {
				this.totalAmount = totalAmount;
			}

			public String getNotAmount() {
				return notAmount;
			}

			public void setNotAmount(String notAmount) {
				this.notAmount = notAmount;
			}

			public String getAlreadyAmount() {
				return alreadyAmount;
			}

			public void setAlreadyAmount(String alreadyAmount) {
				this.alreadyAmount = alreadyAmount;
			}
		}
	}
}