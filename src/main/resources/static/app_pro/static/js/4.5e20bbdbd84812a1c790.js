webpackJsonp([4],{"19kb":function(e,t){},R7bE:function(e,t,a){e.exports=a.p+"static/img/detail_banner.519d921.png"},X2Vr:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("Xxa5"),i=a.n(n),r=a("exGp"),s=a.n(r),c={name:"detail",data:function(){return{descPic:a("R7bE"),detail:[]}},watch:{$route:function(e,t){var a=this;return s()(i.a.mark(function n(){var r;return i.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:if(e.query.id===t.query.id){n.next=5;break}return n.next=3,a.$http.get(a.$HOST+"/open/health/message/center/app/message/detail?id="+e.query.id);case 3:r=n.sent,a.detail=r.result.message;case 5:case"end":return n.stop()}},n,a)}))()}},created:function(){var e=this;return s()(i.a.mark(function t(){var a,n,r;return i.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return a=e,n=e.$route.query.id,t.next=4,e.$http.get(e.$HOST+"/open/health/message/center/app/message/detail?id="+n);case 4:r=t.sent,a.detail=r.result.message;case 6:case"end":return t.stop()}},t,e)}))()}},d={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"announce_detail"}},[a("new-layout",[a("template",{slot:"top"},[a("announce-header",[e._v(e._s(e.detail.title))])],1),e._v(" "),a("template",{slot:"center"},[a("div",{staticClass:"detail_con"},[a("div",{staticClass:"origin-date"},[a("span",[e._v("发布时间："+e._s(e.timeStamp(e.detail.publishTime)))])]),e._v(" "),a("div",{staticClass:"desc"},[a("div",{staticClass:"banner"},[a("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.getImgURL(e.detail.thumbnailUrl.pelativePath),expression:"getImgURL(detail.thumbnailUrl.pelativePath)"}]})]),e._v(" "),a("div",{staticClass:"con",domProps:{innerHTML:e._s(e.detail.content)}},[e._v("detail.content")])]),e._v(" "),a("new-recommend")],1)])],2)],1)},staticRenderFns:[]};var l=a("VU/8")(c,d,!1,function(e){a("19kb")},"data-v-b099d5ca",null);t.default=l.exports}});
//# sourceMappingURL=4.5e20bbdbd84812a1c790.js.map