webpackJsonp([6],{apGg:function(e,a){},pjFb:function(e,a,s){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var t=s("Xxa5"),i=s.n(t),r=s("exGp"),n=s.n(r),o=(s("Fd2+"),{data:function(){return{sex:"1",age:"47",weight:"95",height:"178",sbp:"156",dbp:"90",pbg:"16.1",fbc:"10.8",waistline:"100",diabetes:"1",strokeOrTia:"1",tc_mmol:"7.8",smoke:"1",idl:"4.12",hdl:"4.5",family_cvd:"1",sports:"1",vegOrFruits:"1",hyperglycemia:"1",family_diabetes:"1",hormone:"1",menopause:"1",drinking:"1",diarrhea:"1",rarelyBask:"1",rarelysports:"1",family_osteoporosis:"1",family_hypertension:"0",dyazide:"1",contentshow:!1,results:[],diabetesArray:[],hypertension:[],af:[],chd:[],icvd:[],cvd:[],osteoporosis:[],afSuggest:"",diabetesSuggest:"",hypertensionSuggest:"",chdSuggest:"",icvdSuggest:"",cvdSuggest:"",osteoporosisSuggest:""}},mounted:{},methods:{replaces:function(e){return e.replace(/[@#]/g,"<br/>")},closeds:function(){this.contentshow=!1},submit:function(){var e=this;return n()(i.a.mark(function a(){var s,t;return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return s={sex:e.sex,age:e.age,weight:e.weight,height:e.height,sbp:e.sbp,dbp:e.dbp,pbg:e.pbg,fbc:e.fbc,waistline:e.waistline,diabetes:e.diabetes,strokeOrTia:e.strokeOrTia,tc_mmol:e.tc_mmol,smoke:e.smoke,idl:e.idl,hdl:e.hdl,family_cvd:e.family_cvd,sports:e.sports,vegOrFruits:e.vegOrFruits,hyperglycemia:e.hyperglycemia,family_diabetes:e.family_diabetes,hormone:e.hormone,menopause:e.menopause,drinking:e.drinking,diarrhea:e.diarrhea,rarelyBask:e.rarelyBask,rarelysports:e.rarelysports,family_osteoporosis:e.family_osteoporosis,family_hypertension:e.family_hypertension,dyazide:e.dyazide},a.next=3,e.$http.post(e.$HOST+"/open/xk/evaluate",s);case 3:(t=a.sent)&&(e.diabetesArray=JSON.parse(t.result.diabetes.result),e.diabetesSuggest=e.replaces(e.diabetesArray.suggest),e.hypertension=JSON.parse(t.result.hypertension.result),e.hypertensionSuggest=e.replaces(e.hypertension.suggest),e.af=JSON.parse(t.result.af.result),e.afSuggest=e.replaces(e.af.suggest),e.chd=JSON.parse(t.result.chd.result),e.chdSuggest=e.replaces(e.chd.suggest),e.icvd=JSON.parse(t.result.icvd.result),e.icvdSuggest=e.replaces(e.icvd.suggest),e.cvd=JSON.parse(t.result.cvd.result),e.cvdSuggest=e.replaces(e.cvd.suggest),e.osteoporosis=JSON.parse(t.result.osteoporosis.result),e.osteoporosisSuggest=e.replaces(e.osteoporosis.suggest),e.contentshow=!0);case 5:case"end":return a.stop()}},a,e)}))()}},created:function(){}}),v={render:function(){var e=this,a=e.$createElement,s=e._self._c||a;return s("div",{attrs:{id:"encyclopedias"}},[s("new-layout",[s("template",{slot:"top"},[s("announce-header",[e._v("评估")])],1),e._v(" "),s("template",{slot:"center"},[s("ul",[s("li",[s("span",[e._v("性别")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.sex,callback:function(a){e.sex=a},expression:"sex"}},[s("van-radio",{attrs:{name:"1"}},[e._v("男")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("女")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("年龄")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.age,expression:"age"}],attrs:{type:"text",placeholder:"请输入年龄"},domProps:{value:e.age},on:{input:function(a){a.target.composing||(e.age=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("体重")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.weight,expression:"weight"}],attrs:{type:"text",placeholder:"请输入体重"},domProps:{value:e.weight},on:{input:function(a){a.target.composing||(e.weight=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("身高")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.height,expression:"height"}],attrs:{type:"text",placeholder:"请输入身高"},domProps:{value:e.height},on:{input:function(a){a.target.composing||(e.height=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("高压（收缩压）")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.sbp,expression:"sbp"}],attrs:{type:"text",placeholder:"请输入高压"},domProps:{value:e.sbp},on:{input:function(a){a.target.composing||(e.sbp=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v(" 低压（舒张压）")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.dbp,expression:"dbp"}],attrs:{type:"text",placeholder:"请输入低压"},domProps:{value:e.dbp},on:{input:function(a){a.target.composing||(e.dbp=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("餐后血糖")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.pbg,expression:"pbg"}],attrs:{type:"text",placeholder:"请输入餐后血糖"},domProps:{value:e.pbg},on:{input:function(a){a.target.composing||(e.pbg=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("空腹血糖")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.fbc,expression:"fbc"}],attrs:{type:"text",placeholder:"请输入空腹血糖"},domProps:{value:e.fbc},on:{input:function(a){a.target.composing||(e.fbc=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("腰围 单位:CM")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.waistline,expression:"waistline"}],attrs:{type:"text",placeholder:"请输入腰围"},domProps:{value:e.waistline},on:{input:function(a){a.target.composing||(e.waistline=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("患糖尿病")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.diabetes,callback:function(a){e.diabetes=a},expression:"diabetes"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("发作过中风或脑缺血")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.strokeOrTia,callback:function(a){e.strokeOrTia=a},expression:"strokeOrTia"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("空腹血清总胆固醇")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.tc_mmol,expression:"tc_mmol"}],attrs:{type:"text",placeholder:"请输入空腹血清总胆固醇"},domProps:{value:e.tc_mmol},on:{input:function(a){a.target.composing||(e.tc_mmol=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("吸烟")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.smoke,callback:function(a){e.smoke=a},expression:"smoke"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("低密度脂蛋白")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.idl,expression:"idl"}],attrs:{type:"text",placeholder:"请输入低密度脂蛋白"},domProps:{value:e.idl},on:{input:function(a){a.target.composing||(e.idl=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("高密度脂蛋白")]),e._v(" "),s("span",[s("input",{directives:[{name:"model",rawName:"v-model",value:e.hdl,expression:"hdl"}],attrs:{type:"text",placeholder:"请输入高密度脂蛋白"},domProps:{value:e.hdl},on:{input:function(a){a.target.composing||(e.hdl=a.target.value)}}})])]),e._v(" "),s("li",[s("span",[e._v("CVD家族史")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.family_cvd,callback:function(a){e.family_cvd=a},expression:"family_cvd"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v(" 每天锻炼30分钟以上")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.sports,callback:function(a){e.sports=a},expression:"sports"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("每天吃水果或蔬菜")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.vegOrFruits,callback:function(a){e.vegOrFruits=a},expression:"vegOrFruits"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("服用降压药")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.dyazide,callback:function(a){e.dyazide=a},expression:"dyazide"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("高血糖")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.hyperglycemia,callback:function(a){e.hyperglycemia=a},expression:"hyperglycemia"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("有糖尿病家族史")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.family_diabetes,callback:function(a){e.family_diabetes=a},expression:"family_diabetes"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("连续6个月以上服用激素类药品")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.hormone,callback:function(a){e.hormone=a},expression:"hormone"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("45岁前绝经")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.menopause,callback:function(a){e.menopause=a},expression:"menopause"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("饮酒")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.drinking,callback:function(a){e.drinking=a},expression:"drinking"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("腹泻、腹痛或大便习惯")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.diarrhea,callback:function(a){e.diarrhea=a},expression:"diarrhea"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("不经常晒太阳")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.rarelyBask,callback:function(a){e.rarelyBask=a},expression:"rarelyBask"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("不经常参加运动锻炼")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.rarelysports,callback:function(a){e.rarelysports=a},expression:"rarelysports"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("有质疏松家族史")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.family_osteoporosis,callback:function(a){e.family_osteoporosis=a},expression:"family_osteoporosis"}},[s("van-radio",{attrs:{name:"1"}},[e._v("是")]),e._v(" "),s("van-radio",{attrs:{name:"0"}},[e._v("否")])],1)],1)]),e._v(" "),s("li",[s("span",[e._v("有高血压家族史")]),e._v(" "),s("span",{staticClass:"mt15"},[s("van-radio-group",{model:{value:e.family_hypertension,callback:function(a){e.family_hypertension=a},expression:"family_hypertension"}},[s("van-radio",{attrs:{name:"0"}},[e._v("父母皆无")]),e._v(" "),s("van-radio",{attrs:{name:"1"}},[e._v("父或母有")]),e._v(" "),s("van-radio",{attrs:{name:"2"}},[e._v("父母皆有")])],1)],1)])]),e._v(" "),s("div",{staticClass:"button-div",on:{click:function(a){e.submit()}}},[e._v("提交")])]),e._v(" "),s("template",{slot:"bottom"},[s("van-popup",{attrs:{position:"right"},model:{value:e.contentshow,callback:function(a){e.contentshow=a},expression:"contentshow"}},[s("div",{attrs:{id:"cancel"},on:{click:function(a){e.closeds()}}},[e._v("取消")]),e._v(" "),s("div",[s("p",[s("b",[e._v("评估名称")]),e._v("：糖尿病风险评估")]),e._v(" "),s("p",[s("b",[e._v("风险等级")]),e._v("："),s("span",{class:"低风险"==e.diabetesArray.riskLevel?"lowrisk":"中等风险"==e.diabetesArray.riskLevel?"mediumrisk":"highrisk"},[e._v(e._s(e.diabetesArray.riskLevel))])]),e._v(" "),s("p",[s("b",[e._v("分析建议")]),e._v("："),s("span",{domProps:{innerHTML:e._s(e.diabetesSuggest)}})])]),e._v(" "),s("div",[s("p",[s("b",[e._v("评估名称")]),e._v("：高血压风险评估")]),e._v(" "),s("p",[s("b",[e._v("风险等级")]),e._v("：\n                "),s("span",{class:"低风险"==e.hypertension.riskLevel?"lowrisk":"中等风险"==e.hypertension.riskLevel?"mediumrisk":"highrisk"},[e._v(e._s(e.hypertension.riskLevel))])]),e._v(" "),s("p",[s("b",[e._v("分析建议")]),e._v("："),e._v(" "),s("span",{directives:[{name:"htnl",rawName:"v-htnl",value:e.hypertensionSuggest,expression:"hypertensionSuggest"}]})])]),e._v(" "),s("div",[s("p",[s("b",[e._v("评估名称")]),e._v("：心房颤动后中风风险评估")]),e._v(" "),s("p",[s("b",[e._v("风险等级")]),e._v("："),s("span",{class:"低风险"==e.af.riskLevel?"lowrisk":"中等风险"==e.af.riskLevel?"mediumrisk":"highrisk"},[e._v(e._s(e.af.riskLevel))])]),e._v(" "),s("p",[s("b",[e._v("分析建议")]),e._v("： "),s("span",{domProps:{innerHTML:e._s(e.afSuggest)}})])]),e._v(" "),s("div",[s("p",[s("b",[e._v("评估名称")]),e._v("：冠心病风险评估")]),e._v(" "),s("p",[s("b",[e._v("风险等级")]),e._v("："),s("span",{class:"低风险"==e.chd.riskLevel?"lowrisk":"中等风险"==e.chd.riskLevel?"mediumrisk":"highrisk"},[e._v(e._s(e.chd.riskLevel))])]),e._v(" "),s("p",[s("b",[e._v("分析建议")]),e._v("："),s("span",{domProps:{innerHTML:e._s(e.chdSuggest)}})])]),e._v(" "),s("div",[s("p",[s("b",[e._v("评估名称")]),e._v("：缺血性心血管病风险评估")]),e._v(" "),s("p",[s("b",[e._v("风险等级")]),e._v("："),s("span",{class:"低风险"==e.icvd.riskLevel?"lowrisk":"中等风险"==e.icvd.riskLevel?"mediumrisk":"highrisk"},[e._v(e._s(e.icvd.riskLevel))])]),e._v(" "),s("p",[s("b",[e._v("分析建议")]),e._v("："),s("span",{domProps:{innerHTML:e._s(e.icvdSuggest)}})])]),e._v(" "),s("div",[s("p",[s("b",[e._v("评估名称")]),e._v("：五年内糖尿病患者发生心脑血管并发症风险评估")]),e._v(" "),s("p",[s("b",[e._v("风险等级")]),e._v("："),s("span",{class:"低风险"==e.cvd.riskLevel?"lowrisk":"中等风险"==e.cvd.riskLevel?"mediumrisk":"highrisk"},[e._v(e._s(e.cvd.riskLevel))])]),e._v(" "),s("p",[s("b",[e._v("分析建议")]),e._v("："),s("span",{domProps:{innerHTML:e._s(e.cvdSuggest)}})])]),e._v(" "),s("div",[s("p",[s("b",[e._v("评估名称")]),e._v("：骨质疏松症风险评估")]),e._v(" "),s("p",[s("b",[e._v("风险等级")]),e._v("："),s("span",{class:"低风险"==e.osteoporosis.riskLevel?"lowrisk":"中等风险"==e.osteoporosis.riskLevel?"mediumrisk":"highrisk"},[e._v(e._s(e.osteoporosis.riskLevel))])]),e._v(" "),s("p",[s("b",[e._v("分析建议")]),e._v("："),e._v(" "),s("span",{domProps:{innerHTML:e._s(e.osteoporosisSuggest)}})])])])],1)],2)],1)},staticRenderFns:[]};var l=s("VU/8")(o,v,!1,function(e){s("apGg")},"data-v-f545a506",null);a.default=l.exports}});
//# sourceMappingURL=6.72bb73d79915092d45b2.js.map