webpackJsonp([11],{HLOe:function(e,t){},dXM8:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={data:function(){return{targetId:"",activeNames:["1"],message:"健。",result:[],result1:[],result2:[],result1Show:!1,resultShow:!1}},methods:{parseEvaluateContent:function(e){var t=e.suggest;"hypertension"!=e.code&&"osteoporosis"!=e.code&&"diabetes"!=e.code&&"af"!=e.code&&"icvd"!=e.code&&"cvd"!=e.code&&"chd"!=e.code||(t=t.replace(/(2\. *运动：|3\. *吸烟：|4\. *饮酒：|5\. *心理：)/g,"@#$1"));for(var s=t.split("@#"),r=[],a=0;a<s.length;a++){var n=s[a];(n=$.trim(n))&&n.length>0&&"3.吸烟："!==n&&r.push(n)}var l=[];for(a=0;a<r.length;a++)l.push("\t"+r[a]+"\n");return console.log(l),l},search:function(e){if(!e)return this.$toast("身份证不能为空"),!1;var t={targetId:e},s=this;this.$http.get(this.$HOST+"/open/xk/evaluate/simple/record",t).then(function(e){var t=[];if(JSON.parse(e.result.correctPrescription))s.result=JSON.parse(e.result.correctPrescription),s.resultShow=!0;else{s.result1=JSON.parse(e.result.prescription),s.result=JSON.parse(e.result.prescription);for(var r=0;r<s.result1.length;r++)s.parseEvaluateContent(s.result1[r])&&t.push(s.parseEvaluateContent(s.result1[r]));s.result2=t,s.result1Show=!0}})}},filters:{},created:function(){}},a={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{attrs:{id:"encyclopedias"}},[s("new-layout",[s("template",{slot:"top"},[s("announce-header",[e._v("评估")])],1),e._v(" "),s("template",{slot:"center"},[s("ul",[s("li",{staticClass:"identity mt15"},[s("span",[e._v("身份证")]),e._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:e.targetId,expression:"targetId"}],attrs:{type:"text",placeholder:"请输入身份证"},domProps:{value:e.targetId},on:{input:function(t){t.target.composing||(e.targetId=t.target.value)}}})]),e._v(" "),s("div",{staticClass:"identity_btn",on:{click:function(t){e.search(e.targetId)}}},[e._v("查询")])]),e._v(" "),e._l(e.result1,function(t,r){return e.result1Show?s("van-collapse",{key:r,attrs:{accordion:""},model:{value:e.activeNames,callback:function(t){e.activeNames=t},expression:"activeNames"}},[s("van-collapse-item",{attrs:{name:r}},[s("div",{attrs:{slot:"title"},slot:"title"},[e._v(e._s(t.name)+"  "),s("span",{class:"低风险"==t.riskLevelName?"lowrisk":"中等风险"==t.riskLevelName?"mediumrisk":"highrisk"},[e._v(e._s(t.riskLevelName))])]),e._v(" "),s("div",[s("b",[e._v("分析建议：")]),e._v(" "),e._l(e.result2,function(t,r){return s("div",{key:r,staticClass:"indent"},e._l(t,function(t,r){return s("span",{key:r,domProps:{innerHTML:e._s(t)}})}))})],2)])],1):e._e()}),e._v(" "),e._l(e.result,function(t,r){return e.resultShow?s("van-collapse",{key:r,attrs:{accordion:""},model:{value:e.activeNames,callback:function(t){e.activeNames=t},expression:"activeNames"}},[s("van-collapse-item",{attrs:{name:r}},[s("div",{attrs:{slot:"title"},slot:"title"},[e._v(e._s(t.name)+"  "),s("span",{class:"低风险"==t.riskLevelName?"lowrisk":"中等风险"==t.riskLevelName?"mediumrisk":"highrisk"},[e._v(e._s(t.riskLevelName))])]),e._v(" "),s("p",[s("b",[e._v("分析建议：")])]),s("div",{staticClass:"indent",domProps:{innerHTML:e._s(t.suggest.replace(/\n|\r\n/g,"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"))}}),s("p")])],1):e._e()})],2),e._v(" "),s("template",{slot:"bottom"})],2)],1)},staticRenderFns:[]};var n=s("VU/8")(r,a,!1,function(e){s("HLOe")},"data-v-7400954a",null);t.default=n.exports}});
//# sourceMappingURL=11.1d2c772d4a245dab699f.js.map