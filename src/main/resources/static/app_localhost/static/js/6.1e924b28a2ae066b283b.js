webpackJsonp([6],{"9zje":function(e,a,t){e.exports=t.p+"static/img/mask.7c11b48.png"},LBnI:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var s=t("Xxa5"),i=t.n(s),n=t("exGp"),r=t.n(n),c={name:"slider",data:function(){return{mask:t("9zje"),sliderList:[]}},created:function(){var e=this;return r()(i.a.mark(function a(){var t,s;return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return t=e,a.next=3,e.$http.get(e.$HOST+"/open/health/message/center/app/videoSlip");case 3:s=a.sent,t.sliderList=s.result.videos;case 5:case"end":return a.stop()}},a,e)}))()}},l={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"slider_wrap"},[t("div",{staticClass:"video_slider"},[t("van-swipe",{attrs:{autoplay:3e3}},e._l(e.sliderList,function(a,s){return t("van-swipe-item",{key:s},[t("div",{on:{click:function(t){e.$router.push({path:"/videoDetail",query:{id:a.id}})}}},[t("div",{staticClass:"banner"},[t("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.getImgURL(a.showImageUrl.pelativePath),expression:"getImgURL(item.showImageUrl.pelativePath)"}]})]),e._v(" "),t("div",{staticClass:"bc"},[t("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.mask,expression:"mask"}]})]),e._v(" "),t("div",{staticClass:"banner_info"},[t("p",[e._v(e._s(a.name))]),e._v(" "),t("p",[t("span",[e._v(e._s(a.label))]),e._v(" "),t("span",[e._v(e._s(a.createTime?a.createTime:""))])])])])])}))],1)])},staticRenderFns:[]};var v=t("VU/8")(c,l,!1,function(e){t("MFfp")},"data-v-009a012c",null);a.default=v.exports},MFfp:function(e,a){}});
//# sourceMappingURL=6.1e924b28a2ae066b283b.js.map