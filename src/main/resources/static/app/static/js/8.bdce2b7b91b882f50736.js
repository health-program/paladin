webpackJsonp([8],{"F/4O":function(s,e){},odeh:function(s,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=t("Xxa5"),d=t.n(r),a=t("exGp"),_=t.n(a),l={data:function(){return{activeNames:["0"],res:"",dd_lArray:[],dd_dArray:[],dd_saArray:[],dd_mArray:[],dd_nArray:[],dd_cArray:[],dd_sArray:[],dd_rArray:[],dd_gArray:[],dd_dArray1:[],disease:":",classification:"",reason:"",dd_cArrayShow:!1,dd_sArrayShow:!1,dd_rArrayShow:!1,dd_lArrayShow:!1,dd_dArrayShow:!1,dd_dArray1Show:!1,dd_mArrayShow:!1,dd_saArrayShow:!1,dd_nArrayShow:!1,dd_gArrayShow:!1}},methods:{init:function(){var s=this;return _()(d.a.mark(function e(){var t,r;return d.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t=s,r=s.$route.query.code,e.next=4,s.$http.get(s.$HOST+"/open/xk/symptom?code="+r);case 4:s.res=e.sent,"index"==s.res.result.type?(s.disease="检查目的",s.classification="判定标准",s.reason="可能原因"):(s.disease="疾病概述",s.classification="疾病分类",s.reason="病因"),s.res.result.knowledge.dd_l&&(s.dd_cArrayShow=!0,s.common(s.res.result.knowledge.dd_l,t.dd_lArray)),s.res.result.knowledge.dd_s&&(s.dd_sArrayShow=!0,s.common(s.res.result.knowledge.dd_s,t.dd_sArray)),s.res.result.knowledge.dd_l&&(s.dd_lArrayShow=!0,s.common(s.res.result.knowledge.dd_l,t.dd_lArray)),s.res.result.knowledge.dd_d_s&&(s.dd_dArrayShow=!0,s.common(s.res.result.knowledge.dd_d_s,t.dd_dArray)),s.res.result.knowledge.dd_d_a&&(s.dd_dArray1Show=!0,s.common(s.res.result.knowledge.dd_d_a,t.dd_dArray1)),s.res.result.knowledge.dd_sa&&(s.dd_saArrayShow=!0,s.common(s.res.result.knowledge.dd_sa,t.dd_saArray)),s.res.result.knowledge.dd_m&&(s.dd_mArrayShow=!0,s.common(s.res.result.knowledge.dd_m,t.dd_mArray)),s.res.result.knowledge.dd_n&&(s.dd_nArrayShow=!0,s.common(s.res.result.knowledge.dd_n,t.dd_nArray)),s.res.result.knowledge.dd_c&&(s.dd_cArrayShow=!0,s.common(s.res.result.knowledge.dd_c,t.dd_cArray)),s.res.result.knowledge.dd_r&&(s.dd_rArrayShow=!0,s.common(s.res.result.knowledge.dd_r,t.dd_rArray)),s.res.result.knowledge.dd_g&&(s.dd_gArrayShow=!0,s.common(s.res.result.knowledge.dd_g,t.dd_gArray));case 17:case"end":return e.stop()}},e,s)}))()},common:function(s,e){for(var t=s.split("@#"),r=0;r<t.length;r++)t[r]&&e.push(t[r].split("#@"))}},created:function(){this.init()}},n={render:function(){var s=this,e=s.$createElement,t=s._self._c||e;return t("div",{attrs:{id:"encyclopedias"}},[t("new-layout",[t("template",{slot:"top"},[t("div",{attrs:{id:"announce_header"}},[t("i",{staticClass:"iconfont icon-fanhui",on:{click:function(e){s.$router.push("encyclopedias")}}}),s._v(" "),t("span",{staticClass:"tit text-overflow"},[s._t("default",[s._v("指标详情")])],2),s._v(" "),t("i",{staticClass:"iconfont icon-fanhui",staticStyle:{opacity:"0"}})])]),s._v(" "),t("template",{slot:"center"},[t("header",[s._v(s._s(s.res.result.knowledge.dn))]),s._v(" "),t("table",{staticClass:"fz24"},[t("tr",[t("td",{staticClass:"width100 fz24"},[s._v("疾病名称:")]),s._v(" "),t("td",[s._v(s._s(s.res.result.knowledge.dn))])]),s._v(" "),t("tr",[t("td",{staticClass:"width100 fz24"},[s._v("所属科室:")]),s._v(" "),t("td",[s._v(s._s(s.res.result.knowledge.ad))])]),s._v(" "),t("tr",[t("td",{staticClass:"width100 fz24",domProps:{innerHTML:s._s(s.disease)}}),s._v(" "),t("td",[s._v(s._s(s.res.result.knowledge.dm))])]),s._v(" "),t("tr",[t("td",{staticClass:"width100 fz24",domProps:{innerHTML:s._s(s.classification)}}),s._v(" "),t("td",[s._v(s._s(s.res.result.knowledge.dc))])])]),s._v(" "),t("div",{staticClass:"disease_detail"},[s._v("\n        疾病详情\n      ")]),s._v(" "),t("van-collapse",{model:{value:s.activeNames,callback:function(e){s.activeNames=e},expression:"activeNames"}},[s.dd_cArrayShow?t("van-collapse-item",{attrs:{title:s.reason,name:"1"}},s._l(s.dd_cArray,function(e,r){return t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])])})):s._e(),s._v(" "),s.dd_sArrayShow?t("van-collapse-item",{attrs:{title:"症状",name:"2"}},s._l(s.dd_sArray,function(e,r){return t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])])})):s._e(),s._v(" "),s.dd_rArrayShow?t("van-collapse-item",{attrs:{title:"风险或并发症",name:"3"}},s._l(s.dd_rArray,function(e,r){return t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])])})):s._e(),s._v(" "),s.dd_lArrayShow?t("van-collapse-item",{attrs:{title:"专家建议",name:"4"}},[t("van-collapse-item",{attrs:{title:"生活方式",name:"5"}},s._l(s.dd_lArray,function(e,r){return t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])])})),s._v(" "),t("van-collapse-item",{attrs:{title:"饮食建议",name:"6"}},[t("span",{staticClass:"suit"},[s._v("宜")]),s._v(" "),s._l(s.dd_dArray,function(e,r){return s.dd_dArrayShow?t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])]):s._e()}),s._v(" "),t("span",{staticClass:"avoid"},[s._v("忌")]),s._v(" "),s._l(s.dd_dArray1,function(e,r){return s.dd_dArray1Show?t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])]):s._e()})],2),s._v(" "),s.dd_mArrayShow?t("van-collapse-item",{attrs:{title:"医疗保健",name:"8"}},s._l(s.dd_mArray,function(e,r){return t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])])})):s._e()],1):s._e(),s._v(" "),s.dd_saArrayShow?t("van-collapse-item",{attrs:{title:"运动建议",name:"9"}},s._l(s.dd_saArray,function(e,r){return t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])])})):s._e(),s._v(" "),s.dd_nArrayShow?t("van-collapse-item",{attrs:{title:"生活常识",name:"10"}},s._l(s.dd_nArray,function(e,r){return t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])])})):s._e(),s._v(" "),s.dd_gArrayShow?t("van-collapse-item",{attrs:{title:"就医复查指南",name:"11"}},s._l(s.dd_gArray,function(e,r){return t("li",{staticClass:"sugges_li"},[t("p",{staticClass:"title"},[s._v(s._s(e[0]))]),s._v(" "),t("p",[s._v(s._s(e[1]))])])})):s._e()],1)],1)],2)],1)},staticRenderFns:[]};var i=t("VU/8")(l,n,!1,function(s){t("F/4O")},"data-v-a44c7f52",null);e.default=i.exports}});
//# sourceMappingURL=8.bdce2b7b91b882f50736.js.map