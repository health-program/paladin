webpackJsonp([3],{KXen:function(e,t){},odeh:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=s("Xxa5"),a=s.n(r),l=s("exGp"),i=s.n(l),d=s("Fd2+"),_={data:function(){return{activeNames:["0"],res:"",dd_crray:[],dd_lArray:[],dd_dArray:[],dd_saArray:[],dd_mArray:[],dd_nArray:[],dd_cArray:[],dd_sArray:[],dd_rArray:[],dd_gArray:[],dd_dArray1:[],disease:":",classification:"",reason:"",dd_cArrayShow:!1,dd_sArrayShow:!1,dd_rArrayShow:!1,dd_lArrayShow:!1,dd_dArrayShow:!1,dd_dArray1Show:!1,dd_mArrayShow:!1,dd_saArrayShow:!1,dd_nArrayShow:!1,dd_gArrayShow:!1}},methods:{init:function(){var e=this;return i()(a.a.mark(function t(){var s,r,l,i;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return s=e,r=e.$route.query.code,t.next=4,e.$http.get(e.$HOST+"/open/xk/symptom?code="+r);case 4:if(l=t.sent,e.res=l,"index"==e.res.result.base.type?(e.disease="检查目的",e.classification="判定标准",e.reason="可能原因"):(e.disease="疾病概述",e.classification="疾病分类",e.reason="病因"),e.res.result.detail){for(e.dd_cArrayShow=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].cause=JSON.parse(e.res.result.detail[i].cause);s.dd_crray=s.res.result.detail}if(e.res.result.detail){for(i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].sportsAdvice=JSON.parse(e.res.result.detail[i].sportsAdvice);e.dd_saArrayShow=!0,s.dd_saArray=s.res.result.detail}if(e.res.result.detail){for(e.dd_sArrayShow=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].symptom=JSON.parse(e.res.result.detail[i].symptom);s.dd_saArray=s.res.result.detail}if(e.res.result.detail){for(e.dd_lArrayShow=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].lifestyle=JSON.parse(e.res.result.detail[i].lifestyle);s.dd_lArray=e.res.result.detail}if(e.res.result.detail){for(e.dd_dArrayShow=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].dietaryShouldEat=JSON.parse(e.res.result.detail[i].dietaryShouldEat);s.dd_dArray=e.res.result.detail}if(e.res.result.detail){for(e.dd_dArray1Show=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].dietaryAvoidEat=JSON.parse(e.res.result.detail[i].dietaryAvoidEat);s.dd_dArray1=e.res.result.detail}if(e.res.result.detail){for(e.dd_mArrayShow=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].medicalInsurance=JSON.parse(e.res.result.detail[i].medicalInsurance);s.dd_mArray=e.res.result.detail}if(e.res.result.detail){for(e.dd_nArrayShow=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].lifeCommonSense=JSON.parse(e.res.result.detail[i].lifeCommonSense);s.dd_nArray=e.res.result.detail}if(e.res.result.detail){for(e.dd_rArrayShow=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].risk=JSON.parse(e.res.result.detail[i].risk);s.dd_rArray=e.res.result.detail}if(e.res.result.detail){for(e.dd_gArrayShow=!0,i=0;i<e.res.result.detail.length;i++)e.res.result.detail[i].medicalReviewGuide=JSON.parse(e.res.result.detail[i].medicalReviewGuide);s.dd_gArray=e.res.result.detail}case 17:case"end":return t.stop()}},t,e)}))()}},created:function(){this.init()}},n={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("van-collapse",{model:{value:e.activeNames,callback:function(t){e.activeNames=t},expression:"activeNames"}},[e.dd_cArrayShow?s("van-collapse-item",{attrs:{title:e.reason,name:"1"}},e._l(e.dd_crray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},e._l(t.cause,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))})):e._e(),e._v(" "),e.dd_sArrayShow?s("van-collapse-item",{attrs:{title:"症状",name:"2"}},e._l(e.dd_sArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},e._l(t.symptom,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))})):e._e(),e._v(" "),e.dd_rArrayShow?s("van-collapse-item",{attrs:{title:"风险或并发症",name:"3"}},e._l(e.dd_rArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},e._l(t.risk,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))})):e._e(),e._v(" "),e.dd_lArrayShow?s("van-collapse-item",{attrs:{title:"专家建议",name:"4"}},[s("van-collapse-item",{attrs:{title:"生活方式",name:"5"}},e._l(e.dd_lArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},e._l(t.lifestyle,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))})),e._v(" "),s("van-collapse-item",{attrs:{title:"饮食建议",name:"6"}},[s("span",{staticClass:"suit"},[e._v("宜")]),e._v(" "),e._l(e.dd_dArray,function(t,r){return e.dd_dArrayShow?s("li",{key:r,staticClass:"sugges_li"},e._l(t.dietaryShouldEat,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e()}),e._v(" "),s("span",{staticClass:"avoid"},[e._v("忌")]),e._v(" "),e._l(e.dd_dArray1,function(t,r){return e.dd_dArray1Show?s("li",{key:r,staticClass:"sugges_li"},e._l(t.dietaryAvoidEat,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e()})],2),e._v(" "),e.dd_mArrayShow?s("van-collapse-item",{attrs:{title:"医疗保健",name:"8"}},e._l(e.dd_mArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},e._l(t.medicalInsurance,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))})):e._e()],1):e._e(),e._v(" "),e.dd_saArrayShow?s("van-collapse-item",{attrs:{title:"运动建议",name:"9"}},e._l(e.dd_saArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},e._l(t.sportsAdvice,function(t,r){return s("div",{key:r},[e._v("\n         "+e._s(t)+"\n        "),s("p",{staticClass:"title"},[e._v(e._s(t.key)+":"+e._s(t.value)+"<!–"),e._v("–>")]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))})):e._e(),e._v(" "),e.dd_nArrayShow?s("van-collapse-item",{attrs:{title:"生活常识",name:"10"}},e._l(e.dd_nArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},e._l(t.lifeCommonSense,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))})):e._e(),e._v(" "),e.dd_gArrayShow?s("van-collapse-item",{key:e.index,attrs:{title:"就医复查指南",name:"11"}},e._l(e.dd_gArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},e._l(t.medicalReviewGuide,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))})):e._e()],1)},staticRenderFns:[]};var u={data:function(){return{activeNames:["0"],res:"",dd_lArray:[],dd_dArray:[],dd_saArray:[],dd_mArray:[],dd_nArray:[],dd_cArray:[],dd_sArray:[],dd_rArray:[],dd_gArray:[],dd_dArray1:[],disease:":",classification:"",reason:"",dd_cArrayShow:!1,dd_sArrayShow:!1,dd_rArrayShow:!1,dd_lArrayShow:!1,dd_dArrayShow:!1,dd_dArray1Show:!1,dd_mArrayShow:!1,dd_saArrayShow:!1,dd_nArrayShow:!1,dd_gArrayShow:!1,highlowArray:[{index:0,value:"偏高"},{index:1,value:"偏低"}]}},methods:{highlow:function(e){},init:function(){var e=this;return i()(a.a.mark(function t(){var s,r;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return s=e,r=e.$route.query.code,t.next=4,e.$http.get(e.$HOST+"/open/xk/symptom?code="+r);case 4:e.res=t.sent,"index"==e.res.result.type?(e.disease="检查目的",e.classification="判定标准",e.reason="可能原因"):(e.disease="疾病概述",e.classification="疾病分类",e.reason="病因"),e.res.result.detail[0].cause&&(e.dd_cArrayShow=!0,s.dd_cArray=JSON.parse(e.res.result.detail[0].cause)),e.res.result.detail[0].symptom&&(e.dd_sArrayShow=!0,s.dd_sArray=JSON.parse(s.res.result.detail[0].symptom)),e.res.result.detail[0].lifestyle&&(e.dd_lArrayShow=!0,s.dd_lArray=s.res.result.detail[0].lifestyle),e.res.result.detail[0].dietaryShouldEat&&(e.dd_dArrayShow=!0,s.dd_dArray=JSON.parse(s.res.result.detail[0].dietaryShouldEat)),e.res.result.detail[0].dietaryAvoidEat&&(e.dd_dArray1Show=!0,s.dd_dArray1=JSON.parse(e.res.result.detail[0].dietaryAvoidEat)),e.res.result.detail[0].sportsAdvice&&(e.dd_saArrayShow=!0,s.dd_saArray=JSON.parse(e.res.result.detail[0].sportsAdvice)),e.res.result.detail[0].medicalInsurance&&(e.dd_mArrayShow=!0,s.dd_mArray=JSON.parse(e.res.result.detail[0].medicalInsurance)),e.res.result.detail[0].lifeCommonSense&&(e.dd_nArrayShow=!0,s.dd_nArray=JSON.parse(e.res.result.detail[0].lifeCommonSense)),e.res.result.detail[0].risk&&(e.dd_rArrayShow=!0,s.dd_rArray=JSON.parse(e.res.result.detail[0].risk)),e.res.result.detail[0].medicalReviewGuide&&(e.dd_gArrayShow=!0,s.dd_gArray=JSON.parse(e.res.result.detail[0].medicalReviewGuide));case 17:case"end":return t.stop()}},t,e)}))()},common:function(e,t){for(var s=e.split("@#"),r=0;r<s.length;r++)s[r]&&t.push(s[r].split("#@"))}},created:function(){this.init()}},o={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{attrs:{id:"encyclopedias"}},[s("van-collapse",{model:{value:e.activeNames,callback:function(t){e.activeNames=t},expression:"activeNames"}},[e.dd_cArrayShow?s("van-collapse-item",{attrs:{title:e.reason,name:"1"}},e._l(e.dd_cArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),e.dd_sArrayShow?s("van-collapse-item",{attrs:{title:"症状",name:"2"}},e._l(e.dd_sArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),e.dd_rArrayShow?s("van-collapse-item",{attrs:{title:"风险或并发症",name:"3"}},e._l(e.dd_rArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),e.dd_lArrayShow?s("van-collapse-item",{attrs:{title:"专家建议",name:"4"}},[s("van-collapse-item",{attrs:{title:"生活方式",name:"5"}},e._l(e.dd_lArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})),e._v(" "),s("van-collapse-item",{attrs:{title:"饮食建议",name:"6"}},[s("span",{staticClass:"suit"},[e._v("宜")]),e._v(" "),e._l(e.dd_dArray,function(t,r){return e.dd_dArrayShow?s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])]):e._e()}),e._v(" "),s("span",{staticClass:"avoid"},[e._v("忌")]),e._v(" "),e._l(e.dd_dArray1,function(t,r){return e.dd_dArray1Show?s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])]):e._e()})],2),e._v(" "),e.dd_mArrayShow?s("van-collapse-item",{attrs:{title:"医疗保健",name:"8"}},e._l(e.dd_mArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e()],1):e._e(),e._v(" "),e.dd_saArrayShow?s("van-collapse-item",{attrs:{title:"运动建议",name:"9"}},e._l(e.dd_saArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),e.dd_nArrayShow?s("van-collapse-item",{attrs:{title:"生活常识",name:"10"}},e._l(e.dd_nArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),e.dd_gArrayShow?s("van-collapse-item",{attrs:{title:"就医复查指南",name:"11"}},e._l(e.dd_gArray,function(t,r){return s("li",{key:r,staticClass:"sugges_li"},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e()],1)],1)},staticRenderFns:[]};var c={data:function(){return{active:2,activeName1:2,activeNames:["0"],res:"",dd_lArray:[],dd_dArray:[],dd_saArray:[],dd_mArray:[],dd_nArray:[],dd_cArray:[],dd_sArray:[],dd_rArray:[],dd_gArray:[],dd_dArray1:[],disease:":",classification:"",reason:"",dd_cArrayShow:!1,dd_sArrayShow:!1,dd_rArrayShow:!1,dd_lArrayShow:!1,dd_dArrayShow:!1,dd_dArray1Show:!1,dd_mArrayShow:!1,dd_saArrayShow:!1,dd_nArrayShow:!1,dd_gArrayShow:!1,highcomponent:!0,zhibiaoxiangqing:!1,piangaopiandi:!1,gao:!1,highlowArray:[{index:0,value:"偏高"},{index:1,value:"偏低"}],newArray:[]}},components:{high:s("VU/8")(_,n,!1,function(e){s("qQtK")},"data-v-332b5186",null).exports,low:s("VU/8")(u,o,!1,function(e){s("KXen")},"data-v-0153c0cb",null).exports,"van-tab":d.c,"van-tabs":d.d},methods:{highlow:function(e){this.highcomponent=!this.highcomponent},init:function(){var e=this;return i()(a.a.mark(function t(){var s,r,l;return a.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return e,s=e.$route.query.code,t.next=4,e.$http.get(e.$HOST+"/open/xk/symptom?code="+s);case 4:if(r=t.sent,e.res=r,1==e.res.result.base.type?(e.disease="检查目的",e.classification="判定标准",e.reason="可能原因"):(e.disease="疾病概述",e.classification="疾病分类",e.reason="病因"),!e.res.result.detail.cause)for(e.dd_cArrayShow=!0,l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].cause=JSON.parse(e.res.result.detail[l].cause);if(!e.res.result.detail.sportsAdvice){for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].sportsAdvice=JSON.parse(e.res.result.detail[l].sportsAdvice);e.dd_saArrayShow=!0}if(!e.res.result.detail.symptom)for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].symptom=JSON.parse(e.res.result.detail[l].symptom);if(!e.res.result.detail.lifestyle)for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].lifestyle=JSON.parse(e.res.result.detail[l].lifestyle);if(!e.res.result.detail.dietaryShouldEat)for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].dietaryShouldEat=JSON.parse(e.res.result.detail[l].dietaryShouldEat);if(!e.res.result.detail.dietaryAvoidEat)for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].dietaryAvoidEat=JSON.parse(e.res.result.detail[l].dietaryAvoidEat);if(!e.res.result.detail.medicalInsurance)for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].medicalInsurance=JSON.parse(e.res.result.detail[l].medicalInsurance);if(!e.res.result.detail.lifeCommonSense)for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].lifeCommonSense=JSON.parse(e.res.result.detail[l].lifeCommonSense);if(!e.res.result.detail.risk)for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].risk=JSON.parse(e.res.result.detail[l].risk);if(!e.res.result.detail.items)for(l=0;l<e.res.result.detail.length;l++)e.res.result.detail[l].medicalReviewGuide=JSON.parse(e.res.result.detail[l].medicalReviewGuide);e.newArray=e.res.result.detail;case 18:case"end":return t.stop()}},t,e)}))()}},created:function(){this.init()}},v={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{attrs:{id:"encyclopedias"}},[s("new-layout",[s("template",{slot:"top"},[s("div",{attrs:{id:"announce_header"}},[s("i",{staticClass:"iconfont icon-fanhui",on:{click:function(t){e.$router.push("encyclopedias")}}}),e._v(" "),s("span",{staticClass:"tit text-overflow"},[1==e.res.result.base.type?e._t("default",[e._v("指标详情")]):e._e(),e._v(" "),2==e.res.result.base.type?e._t("default",[e._v("疾病详情")]):e._e()],2),e._v(" "),s("i",{staticClass:"iconfont icon-fanhui",staticStyle:{opacity:"0"}})])]),e._v(" "),s("template",{staticStyle:{display:"none"},slot:"center"},[s("header",[e._v(e._s(e.res.result.base.name))]),e._v(" "),s("table",{staticClass:"fz24"},[s("tr",[s("td",{staticClass:"width100 fz24"},[e._v("疾病名称：")]),e._v(" "),s("td",[e._v(e._s(e.res.result.base.name))])]),e._v(" "),s("tr",[s("td",{staticClass:"width100 fz24"},[e._v("所属科室：")]),e._v(" "),s("td",[e._v(e._s(e.res.result.base.department))])]),e._v(" "),s("tr",[s("td",{staticClass:"width100 fz24",domProps:{innerHTML:e._s(e.disease)}}),e._v(" "),1==e.res.result.base.type?s("td",[e._v(e._s(e.res.result.base.inspectionPurpose))]):e._e(),e._v(" "),2==e.res.result.base.type?s("td",[e._v(e._s(e.res.result.base.diseaseOverview))]):e._e()]),e._v(" "),s("tr",[s("td",{staticClass:"width100 fz24",domProps:{innerHTML:e._s(e.classification)}}),e._v(" "),1==e.res.result.base.type?s("td",[e._v(e._s(e.res.result.base.judgementStandard))]):e._e(),e._v(" "),2==e.res.result.base.type?s("td",[e._v(e._s(e.res.result.base.diseaseClassification))]):e._e()])]),e._v(" "),e.zhibiaoxiangqing?s("div",{staticClass:"disease_detail"},[e._v("\n        疾病详情\n      ")]):e._e(),e._v(" "),s("van-tabs",{model:{value:e.activeName1,callback:function(t){e.activeName1=t},expression:"activeName1"}},e._l(e.newArray,function(t,r){return s("van-tab",{key:r,attrs:{title:t.title,name:t.title}},[s("van-collapse",{attrs:{accordion:""},model:{value:e.activeNames,callback:function(t){e.activeNames=t},expression:"activeNames"}},[e.dd_cArrayShow?s("van-collapse-item",{attrs:{title:e.reason,name:"1"}},[s("li",{staticClass:"sugges_li"},e._l(t.cause,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])}))]):e._e(),e._v(" "),t.symptom?s("van-collapse-item",{attrs:{title:"症状",name:"2"}},e._l(t.symptom,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),t.risk?s("van-collapse-item",{attrs:{title:"风险或并发症",name:"3"}},e._l(t.risk,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),t.lifestyle?s("van-collapse-item",{attrs:{title:"生活方式",name:"5"}},e._l(t.lifestyle,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),t.dietaryAdvice?s("van-collapse-item",{attrs:{title:"饮食建议",name:"6"}},[t.dietaryShouldEat?s("span",{staticClass:"suit"},[e._v("宜")]):e._e(),e._v(" "),e._l(t.dietaryShouldEat,function(r,a){return t.dietaryShouldEat?s("div",{key:a},[s("p",{staticClass:"title"},[e._v(e._s(r.key))]),e._v(" "),s("p",[e._v(e._s(r.value))])]):e._e()}),e._v(" "),t.dietaryAvoidEat?s("span",{staticClass:"avoid"},[e._v("忌")]):e._e(),e._v(" "),e._l(t.dietaryAvoidEat,function(r,a){return t.dietaryAvoidEat?s("div",{key:a},[s("p",{staticClass:"title"},[e._v(e._s(r.key))]),e._v(" "),s("p",[e._v(e._s(r.value))])]):e._e()})],2):e._e(),e._v(" "),t.medicalInsurance?s("van-collapse-item",{attrs:{title:"医疗保健",name:"8"}},e._l(t.medicalInsurance,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),t.sportsAdvice?s("van-collapse-item",{attrs:{title:"运动建议",name:"9"}},e._l(t.sportsAdvice,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),t.lifeCommonSense?s("van-collapse-item",{attrs:{title:"生活常识",name:"10"}},e._l(t.lifeCommonSense,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e(),e._v(" "),t.medicalReviewGuide?s("van-collapse-item",{attrs:{title:"就医复查指南",name:"11"}},e._l(t.medicalReviewGuide,function(t,r){return s("div",{key:r},[s("p",{staticClass:"title"},[e._v(e._s(t.key))]),e._v(" "),s("p",[e._v(e._s(t.value))])])})):e._e()],1)],1)}))],1)],2)],1)},staticRenderFns:[]};var y=s("VU/8")(c,v,!1,function(e){s("vCbf")},"data-v-28443f7f",null);t.default=y.exports},qQtK:function(e,t){},vCbf:function(e,t){}});
//# sourceMappingURL=3.83587359f9e9ab97009b.js.map