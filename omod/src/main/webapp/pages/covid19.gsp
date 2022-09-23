<% ui.decorateWith("appui", "standardEmrPage") %>


<%=ui.resourceLinks()%>



<% ui.includeCss("covid19", "fontawesome/css/all.css") %>
<% ui.includeCss("covid19", "flaticon/flaticon.css") %>
<% ui.includeCss("covid19", "flaticon2/flaticon.css") %>
<% ui.includeCss("covid19", "bootstrap.css") %>
<% ui.includeCss("covid19", "all.css") %>
<% ui.includeCss("covid19", "site.css") %>
<% ui.includeCss("covid19", "jquery.dataTables.min.css") %>
<% ui.includeCss("covid19", "buttons.dataTables.min.css") %>
<% ui.includeCss("covid19", "custom.css") %>
<% ui.includeCss("covid19", "css/css.css") %>


<% ui.includeJavascript("covid19", "jquery.js") %>
<% ui.includeJavascript("covid19", "jquery-ui.js") %>
<% ui.includeJavascript("covid19", "bootstrap.bundle.js") %>
<% ui.includeJavascript("covid19", "site.js") %>
<% ui.includeJavascript("covid19", "myAjax.js") %>
<% ui.includeJavascript("covid19", "jquery.dataTables.min.js") %>
<% ui.includeJavascript("covid19", "core.js") %>
<% ui.includeJavascript("covid19", "charts.js") %>
<% ui.includeJavascript("covid19", "sunburst.js") %>
<% ui.includeJavascript("covid19", "highcharts.js") %>
<% ui.includeJavascript("covid19", "js/accessibility.js") %>
<% ui.includeJavascript("covid19", "js/export-data.js") %>
<% ui.includeJavascript("covid19", "js/exporting.js") %>
<% ui.includeJavascript("covid19", "js/series-label.js") %>




<%
def months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"] as String[];

int i=1;
int year = Calendar.getInstance().get(Calendar.YEAR);
%>
<style>
.dataTables_length {
    width: 50%;
    float: right;
    text-align: left !important;
}
</style>

<script type="text/javascript">
    /*
    jq = jQuery
jq(document).ready(function() {
    jq('#tableIdplaceholder').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
} );
*/
</script>

<div class="tab" style="width: 100%;">

</div>
<div class="container-fluid bg-light" style="padding-left: 20px; padding-bottom: 10px">
        <div class="row text-center title">
            <h4 style="text-align: center">Covid 19 Cases Summary</h4>
        </div>

   
                    <div class="row">
                        <div class="col mr-2 pl-0">
                            <div class="widget">
                                <div class="widget-content with-shadow">
                                    <div class="widget-icon">
                                        <i class="flaticon-upload purple"></i>
                                    </div>

                                    
                                    <h4 class="widget-title purple" style="font-size: 0.9rem;">
                                        Total Cases
                                    </h4>
                                    <span class="widget-stat purple " id="totalCases">
                                        ...
                                    </span>
                                   
                                </div>
                            </div>
                        </div>

                        <div class="col mr-2">
                            <div class="widget">
                                <div class="widget-content with-shadow">
                                    <div class="widget-icon">
                                        <i class="text-dark-50 flaticon-users-1 teal"></i>
                                    </div>
                                    <h4 class="widget-title teal">
                                        Confirmed Cases
                                    </h4>
                                    <span class="widget-stat teal" id="confirmedCases">
                                        ...
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="col mr-2">
                            <div class="widget">
                                <div class="widget-content with-shadow">
                                    <div class="widget-icon">
                                        <i class="flaticon2-hourglass-1 blue-dark"></i>
                                    </div>
                                    <h4 class="widget-title blue-dark">
                                        Suspected Cases
                                    </h4>
                                    <span class="widget-stat blue-dark" id="suspectedCases">
                                        ...
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="col mr-2">
                            <div class="widget">
                                <div class="widget-content with-shadow">
                                    <div class="widget-icon">
                                        <i class="flaticon2-list-3 orange"></i>
                                    </div>
                                    <h4 class="widget-title orange">
                                        Probable Cases
                                    </h4>
                                    <span class="widget-stat orange" id="probableCases">
                                        ...
                                    </span>
                                </div>
                            </div>
                        </div>
                        <a class="medium" onclick="openTab(event, 'reports')" href="#">
                            <div class="col mr-2 pr-0">
                                <div class="widget">
                                    <div class="widget-content with-shadow">
                                        <div class="widget-icon">
                                            <i class="text-dark-50 flaticon2-user-outline-symbol green-dark"
                                               style="color: #03051c;"></i>
                                        </div>
                                        <h4 class="widget-title green-dark" style="color: #03051c;">
                                            Total Deaths
                                        </h4>
                                        <span class="widget-stat green-dark" id="totalDeaths" style="color: #03051c;">
                                            ...
                                        </span>
                                    </div>
                                </a>
                                </div>
                            </div>
                        </a> 
                </div>

                
                
                
        

       

</div>
<div class="tab" style="width: 100%;">

</div>



<fieldset>
    <legend>Search Cohort
                <label class="cohortDesc"></label>
    </legend>
        <br /><br />
        
        <div class="row">
            <div class="col-sm-6 col-md-3">
                <strong><input type="radio" name="cohortType" value="monthly" class="cohortSelector" />Monthly</strong>
            </div>
            <div class="col-sm-6 col-md-3"> 
                <strong><input type="radio" name="cohortType" value="quarterlycy" class="cohortSelector" />Quarterly (CY)</strong>
            </div>
            <div class="col-sm-6 col-md-3"> 
                <strong><input type="radio" name="cohortType" value="quarterly" class="cohortSelector" />Quarterly (FY)</strong>
            </div>
            <div class="col-sm-6 col-md-3">
                <strong><input type="radio" name="cohortType"  value="dateRange" class="cohortSelector" />Date Range</strong>
            </div>
            
        </div><br />
        
        <div class="row hidden cohortArea" id="monthly">
            
           <label class="col-sm-6 col-md-2 " ><strong>Month</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
                <select name="month" id="month" class="form-control">
                    <option value="">Select Month</option>
                    <% for(int j=0; j<months.length; j++){%>
                        <option value="<%= j+1 %>"><%= months[j] %></option>
                    <% }%>
                </select>
            </div>
            
            <label class="col-sm-6 col-md-2 "><strong>Year</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
                <select name="monthYear" id="monthYear" class="form-control">
              <% for(int k=year; k>=2000; k--){%>
                 <option value="<%= k %>"><%= k %></option>
                    <% }%>
                </select>
            </div>
            
            
            <div class="col-sm-6 col-md-2">
                <button class="button" style="background: #00463f; color: #fff" id="filterCv19Monthly">Search</button>
            </div>
        </div>
        <div class="row hidden cohortArea" id="quarterlycy">
            
           <label class="col-sm-6 col-md-2 " ><strong>Quarter</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
               <select name="quartercy" id="quartercy" class="form-control">
                   <option value="">Select Quarter</option>
                   <option value="1">Quarter 1</option>
                   <option value="2">Quarter 2</option>
                   <option value="3">Quarter 3</option>
                   <option value="4">Quarter 4</option>
                   
                </select>
            </div>
            
            <label class="col-sm-6 col-md-2 "><strong>Year</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
               <select name="quarterYearcy" id="quarterYearcy" class="form-control">
              <% for(int k=year; k>=2000; k--){%>
                 <option value="<%= k %>">CY <%= k %></option>
                    <% }%>
                </select>
            </div>
            
            
            <div class="col-sm-6 col-md-2">
                <button class="button" style="background: #00463f; color: #fff" id="filterCv19Quarterlycy">Search</button>
            </div>
        </div>
        <div class="row hidden cohortArea" id="quarterly">
            
           <label class="col-sm-6 col-md-2 " ><strong>Quarter</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
               <select name="quarter" id="quarter" class="form-control">
                   <option value="">Select Quarter</option>
                   <option value="1">Quarter 1</option>
                   <option value="2">Quarter 2</option>
                   <option value="3">Quarter 3</option>
                   <option value="4">Quarter 4</option>
                   
                </select>
            </div>
            
            <label class="col-sm-6 col-md-2 "><strong>Year</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
               <select name="quarterYear" id="quarterYear" class="form-control">
              <% for(int k=year; k>=2000; k--){%>
                 <option value="<%= k %>">FY <%= k %></option>
                    <% }%>
                </select>
            </div>
            
            
            <div class="col-sm-6 col-md-2">
                <button class="button" style="background: #00463f; color: #fff" id="filterCv19Quarterly">Search</button>
            </div>
        </div>
        <div class="row hidden cohortArea" id="dateRange">
            
           <label class="col-sm-6 col-md-2 " ><strong>Start Date</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
               <input type="text" class="form-control date" id="startDate" name="startDate"/>
            </div>
            
            <label class="col-sm-6 col-md-2 "><strong>End Date</strong></label>
            <div class="col-sm-6 col-md-3" style="position:relative">
               <input type="text" class="form-control date" id="endDate" name="endDate"/>
            </div>
            
            
            <div class="col-sm-6 col-md-2">
                <button class="button" style="background: #00463f; color: #fff" id="filterCv19">Search</button>
            </div>
        </div>
</fieldset>
 
<br/>



                        
<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist">
      <a class="nav-item nav-link active" id="nav-summary-tab" data-toggle="tab" href="#nav-summary" role="tab" aria-controls="nav-summary" aria-selected="true">Epi Summary</a>
      <a class="nav-item nav-link" id="nav-optimal-tab" data-toggle="tab" href="#nav-optimal" role="tab" aria-controls="nav-optimal" aria-selected="false">Epi Charts</a>
      <a class="nav-item nav-link" id="nav-optimal-tab" data-toggle="tab" href="#nav-optimal-geo" role="tab" aria-controls="nav-optimal" aria-selected="false">Geo Mapping</a>
    </div>
</nav>
<div class="tab-content" id="nav-tabContent">
   <div class="tab-pane fade show active" id="nav-summary" role="tabpanel" aria-labelledby="nav-summary-tab">${ui.includeFragment("covid19", "episummary")}</div>
   <div class="tab-pane fade" id="nav-optimal" role="tabpanel" aria-labelledby="nav-optimal-tab">${ui.includeFragment("covid19", "epicharts")}</div>
   <div class="tab-pane fade" id="nav-optimal-geo" role="tabpanel" aria-labelledby="nav-optimal-tab">${ui.includeFragment("covid19", "geomapping")}</div>
</div>












<script type="text/javascript">

var lastDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var patientsWithSampleObj = {};
var casesObj = {};
var obj_cv_cases_suspected_cases = {};
var arr_cv_cases_suspected_cases = [];
var arr_cv_cases_confirmed_cases = [];
var arr_cv_cases_probable_cases = [];
var arr_cv_cases_POE_screened = [];
var arr_cv_cases_on_admission = [];
var arr_cv_cases_confirmed_deaths = [];
//skipped 11
var arr_cv_cases_suspected_deaths = [];
//skipped 24
var arr_cv_cases_asymptomatic = [];
var arr_cv_cases_sympt = [];
                    var diffCases = [];
var startDate = "";
var endDate = "";

var casesData = [];
var casesDataSeries = [];
var deathDataSeries = [];
var catAxisX = [];




jq = jQuery
jq.get("/<%= ui.contextPath();%>/ms/uiframework/resource/covid19/indicators_def.json", function(data){
            indicatorDescription = data;
            var ind_obj = indicatorDescription["40"];
            
            for(var i = 1; i <= 40; i++){
                if(i==39){
                    var ind_obj22 = indicatorDescription[i];
                    //console.log(i+", "+ind_obj22["IndicatorName"]);
                    
                    jq(".epitableInd"+i).html(ind_obj22["IndicatorName"]);
                    
                    
                    for (var acount = 65; acount <= 68; acount++){
                        var sub39 = i+String.fromCharCode(acount).toLowerCase();
                        var ind_obj22sub39 = indicatorDescription[sub39];
                        //console.log(sub39+", "+ind_obj22sub39["IndicatorName"]);
                        jq(".epitableInd"+sub39).html(ind_obj22sub39["IndicatorName"]);
                    }

                }else{
                    var ind_obj22 = indicatorDescription[i];
                    //console.log(i+", "+ind_obj22["IndicatorName"]);
                    jq(".epitableInd"+i).html(ind_obj22["IndicatorName"]);
                }   
            }
            
            
        })
        
        jq(".cohortSelector").change(function(){
        var selVal = jq(this).val();

        //monthly
        //quarterly
        //dateRange
        //if(selVal=='monthly'){
        //    console.log("Monthly selected");
        //}


        jq(".cohortArea").addClass("hidden");
        jq("#"+selVal).removeClass("hidden");
        var cDesc = jq(".cohortDesc").html();
        if(selVal=='monthly'){jq(".cohortDesc").html(" - Monthly");}
        if(selVal=='quarterlycy'){jq(".cohortDesc").html(" - Calendar Year");}
        if(selVal=='quarterly'){jq(".cohortDesc").html(" - Fiscal Year");}
        if(selVal=='dateRange'){jq(".cohortDesc").html(" - Custom Date Range");}
        console.log("Selected value"+selVal);
    });
    
  
    
    jq("#quartercy").change(function(){
            var selValu = jq(this).val();

            if(selValu==1){jq(".cohortDesc").html(" - Calendar Year Q1(Jan - Mar)");}
            if(selValu==2){jq(".cohortDesc").html(" - Calendar Year Q2(Apr - Jun)");}
            if(selValu==3){jq(".cohortDesc").html(" - Calendar Year Q3(Jul - Sept)");}
            if(selValu==4){jq(".cohortDesc").html(" - Calendar Year Q4(Oct - Dec)");}
            console.log("Selected value"+selValu);
        });
        
        jq("#quarter").change(function(){
            var selValu = jq(this).val();

            if(selValu==1){jq(".cohortDesc").html(" - Fiscal Year Q1(Oct - Dec)");}
            if(selValu==2){jq(".cohortDesc").html(" - Fiscal Year Q2(Jan - Mar)");}
            if(selValu==3){jq(".cohortDesc").html(" - Fiscal Year Q3(Apr - Jun)");}
            if(selValu==4){jq(".cohortDesc").html(" - Fiscal Year Q4(Jul - Sept)");}
            console.log("Selected value"+selValu);
        });
        
        
    
        jq("#filterCv19Monthly").click(function(){
        var month = jq("#month").val() ;
        var year = jq("#monthYear").val();
        var lastDay = lastDays[month-1];
        
        console.log("Month: "+month);
        console.log("Year: "+year);
        
        if(month == 2 && leapYear(year))
        {
            lastDay = 29;
        }
        console.log("lastDay: "+lastDay);
        
        var monthString = (month > 9) ? month : "0"+month;
        startDate = year+"-"+monthString+"-"+"01"
        endDate = year+"-"+monthString+"-"+lastDay;
        
        console.log("Start: "+startDate);
        console.log("End: "+endDate);
        
        getCv19Data();
        
        
        });

        
        
        
        jq(".date").unbind("datepicker");
        jq('.date').datepicker({
              dateFormat: 'yy-mm-dd',
              changeYear: true,
              changeMonth:true,
              yearRange: "-30:+0",
              autoclose: true
          });
          
          
          
          
        jq("#filterCv19Quarterlycy").click(function(){
            var quartercy = jq("#quartercy").val() ;
            var yearcy = jq("#quarterYearcy").val();
           
            console.log("Quarter: "+quartercy);
            console.log("Year: "+yearcy);
            
            var startDateEndDate = getDateRangeForQuartercy(quartercy, yearcy)
            
            startDate = startDateEndDate[0];
            endDate = startDateEndDate[1];
            
            console.log("Start: "+startDate);
            console.log("End: "+endDate);

            
            getCv19Data();
           
        
        });
        
        
        jq("#filterCv19Quarterly").click(function(){
            var quarter = jq("#quarter").val() ;
            var year = jq("#quarterYear").val();
           
            console.log("Quarter: "+quarter);
            console.log("Year: "+year);
            
            var startDateEndDate = getDateRangeForQuarter(quarter, year)
            
            startDate = startDateEndDate[0];
            endDate = startDateEndDate[1];
            
            console.log("Start: "+startDate);
            console.log("End: "+endDate);

            
            getCv19Data();
           
        
        });


        jq("#filterCv19").click(function(e){
            startDate = jq("#startDate").val();
            endDate = jq("#endDate").val();
            
            console.log("Start: "+startDate);
            console.log("End: "+endDate);
            
            getCv19Data();
            
            
        });
        
        
isFemale = (eObj) => {
  return (eObj.gender === 'F') || (eObj.gender === 'Female');
}

isMale = (eObj) => {
  return (eObj.gender === 'M') || (eObj.gender === 'Male');
}

sum = (sum, eObj) => {
  return ((eObj.gender === 'F') || (eObj.gender === 'Female') || (eObj.gender === 'M') || (eObj.gender === 'Male')) ? sum + 1 : sum;
}

            
         function getCv19Data()
   {

arr_cv_cases_suspected_cases.length=0;
arr_cv_cases_confirmed_cases.length=0;
arr_cv_cases_probable_cases.length=0;
arr_cv_cases_POE_screened.length=0;
arr_cv_cases_on_admission.length=0;
arr_cv_cases_confirmed_deaths.length=0;
arr_cv_cases_suspected_deaths.length=0;
arr_cv_cases_asymptomatic.length=0;
arr_cv_cases_sympt.length=0;

            
                console.log("SSSSSSSSSSSStart: "+startDate);
                console.log("EEEEEEEEEEEnd: "+endDate);
            
               myAjax({startDate:startDate, endDate:endDate}, '${ ui.actionLink("covid19", "users", "getAllCasesData") }').then(function(response){
               casesData = JSON.parse(response);
               console.log("back in covidddddddddddddddddddddddddddddddd");
               console.log(casesData);
               
               
               /*
               for(var i=0; i<casesData.length; i++){
          
                    console.log("another area");
                    console.log(casesData[i]["case_id"]);
                    console.log(casesData[i]["case_status"]=='162743');
                    patientsWithSampleObj[i]=casesData[i];
                  
                            
                    if(casesData[i]["case_status"]=='162743'){
                    obj_cv_cases_suspected_cases[i]=casesData[i];
                    }
       
      

                }
                console.log("where is this???????????");
                console.log(patientsWithSampleObj);
                console.log(Object.keys(patientsWithSampleObj).length);

            
            
                for(var i=0; i<Object.keys(patientsWithSampleObj).length; i++){
          
                    console.log("area 2");
                    console.log(patientsWithSampleObj[i]["case_id"]);
                    console.log(patientsWithSampleObj[i]["cv19_case_id"]=='6883');
                 
                  
       

                }
                */

           
                
                
        


for ( var index=0; index<casesData.length; index++ ) {
    if ( casesData[index]["case_status"]=='162743' ) {
        arr_cv_cases_suspected_cases.push( casesData[index] );
    }
    if ( casesData[index]["case_status"]=='166428' ) {
        arr_cv_cases_confirmed_cases.push( casesData[index] );
    }
    if ( casesData[index]["case_status"]=='162863' ) {
        arr_cv_cases_probable_cases.push( casesData[index] );
    }
    if ( casesData[index]["poe_screened"]=='1' ) {
        arr_cv_cases_POE_screened.push( casesData[index] );
    }
    if ( casesData[index]["on_admission"]=='1' ) {
        arr_cv_cases_on_admission.push( casesData[index] );
    }
    if ( casesData[index]["case_status"]=='166428' && casesData[index]["status_d_a"]=='160432' ) {
        arr_cv_cases_confirmed_deaths.push( casesData[index] );
    }
    if ( casesData[index]["case_status"]=='162743' && casesData[index]["status_d_a"]=='160432' ) {
        arr_cv_cases_suspected_deaths.push( casesData[index] );
    }
    if ( casesData[index]["symp_asymp"]=='0' && casesData[index]["poe_screened"]=='1') {
        arr_cv_cases_asymptomatic.push( casesData[index] );
    }
    if ( casesData[index]["symp_asymp"]=='1' && casesData[index]["poe_screened"]=='1') {
        arr_cv_cases_sympt.push( casesData[index] );
    }
    
    
    
}

casesObj[1]=arr_cv_cases_suspected_cases;
casesObj[2]=arr_cv_cases_confirmed_cases;
casesObj[3]="na";
casesObj[4]=arr_cv_cases_probable_cases;
casesObj[5]="na";
casesObj[6]=arr_cv_cases_POE_screened;
casesObj[7]="na";
casesObj[8]=arr_cv_cases_on_admission;
casesObj[9]="na";
casesObj[10]=arr_cv_cases_confirmed_deaths;
casesObj[11]="na";
casesObj[12]=arr_cv_cases_suspected_deaths;
casesObj[13]="na";
casesObj[14]="na";
casesObj[15]="na";
casesObj[16]="na";
casesObj[17]="wip";
casesObj[18]="na";
casesObj[19]="na";
casesObj[20]="wip";
casesObj[21]="na";
casesObj[22]="na";
casesObj[23]="wip";
casesObj[24]="na";
casesObj[25]="na";
casesObj[26]="na";
casesObj[27]="na";
casesObj[28]="na";
casesObj[29]="na";
casesObj[30]="na";
casesObj[31]="na";
casesObj[32]="na";
casesObj[33]="na";
casesObj[34]="na";
casesObj[35]="na";
casesObj[36]="na";
casesObj[37]="na";
casesObj[38]=arr_cv_cases_asymptomatic;
casesObj[39]=arr_cv_cases_sympt;
casesObj[39+String.fromCharCode(65).toLowerCase()]="na";
casesObj[39+String.fromCharCode(66).toLowerCase()]="na";
casesObj[39+String.fromCharCode(67).toLowerCase()]="na";
casesObj[39+String.fromCharCode(68).toLowerCase()]="na";
casesObj[40]="na";

console.log("suspects");
console.log(arr_cv_cases_suspected_cases);
console.log(Object.keys(arr_cv_cases_suspected_cases).length);
console.log("confirmed");
console.log(arr_cv_cases_confirmed_cases);
console.log(Object.keys(arr_cv_cases_confirmed_cases).length);
console.log("probable");
console.log(arr_cv_cases_probable_cases);
console.log(Object.keys(arr_cv_cases_probable_cases).length);
console.log("POE");
console.log(arr_cv_cases_POE_screened);
console.log(Object.keys(arr_cv_cases_POE_screened).length);
console.log("on admission");
console.log(arr_cv_cases_on_admission);
console.log(Object.keys(arr_cv_cases_on_admission).length);
console.log("confirmed deaths");
console.log(arr_cv_cases_confirmed_deaths);
console.log(Object.keys(arr_cv_cases_confirmed_deaths).length);
console.log("suspects deaths");
console.log(arr_cv_cases_suspected_deaths);
console.log(Object.keys(arr_cv_cases_suspected_deaths).length);
console.log("asymptomatic");
console.log(arr_cv_cases_asymptomatic);
console.log(Object.keys(arr_cv_cases_asymptomatic).length);
console.log("symptomatic");
console.log(arr_cv_cases_sympt);
console.log(Object.keys(arr_cv_cases_sympt).length);

console.log("This is the obj");
console.log(casesObj);
console.log(Object.keys(casesObj).length);



/*
for (var key of Object.keys(casesObj)) {
   if((casesObj[key])=='na'){
   console.log("Not available");
   }else{
   for(var i=0; i<Object.keys(casesObj[key]).length; i++){


                        console.log("area 3");
                           console.log(casesObj[key][i]);
                 
    }
    }

}
*/

console.log("area 3");
for (var key of Object.keys(casesObj)) {
   if((casesObj[key])=='na'){
   console.log(key + "Not available");
   jq("#totalEnrolledM"+key).html("").css('background-color', '#e5e7e9');
   console.log("#totalEnrolledM"+key);
   jq("#totalEnrolledF"+key).html("").css('background-color', '#e5e7e9');
   jq("#totalEnrolledTotal"+key).html("").css('background-color', '#e5e7e9');
   
   }else if((casesObj[key])=='wip'){
   console.log(key + "Not available");
   jq("#totalEnrolledM"+key).html("").css('background-color', '#98AFC7');
   console.log("#totalEnrolledM"+key);
   jq("#totalEnrolledF"+key).html("").css('background-color', '#98AFC7');
   jq("#totalEnrolledTotal"+key).html("").css('background-color', '#98AFC7');
   
   }else{
 
   jq("#totalEnrolledM"+key).html(casesObj[key].filter(isMale).reduce(sum,0));
   jq("#totalEnrolledF"+key).html(casesObj[key].filter(isFemale).reduce(sum,0));
   jq("#totalEnrolledTotal"+key).html(casesObj[key].filter(isMale).reduce(sum,0) + casesObj[key].filter(isFemale).reduce(sum,0));

                        console.log(key);
                        console.log(casesObj[key].filter(isFemale).reduce(sum,0));
                 

    }

 

    if((casesObj[key])=='2'){
    jq("#confirmedCases").html(casesObj[key].reduce(sum,0));
    }
    if((casesObj[key])=='1'){
    jq("#suspectedCases").html(casesObj[key].reduce(sum,0));
    }
    if((casesObj[key])=='4'){
    jq("#probableCases").html(casesObj[key].reduce(sum,0));
    }
  

}
jq("#confirmedCases").html(casesObj[2].reduce(sum,0));
jq("#suspectedCases").html(casesObj[1].reduce(sum,0));
jq("#probableCases").html(casesObj[4].reduce(sum,0));
jq("#totalCases").html(casesObj[2].reduce(sum,0) + casesObj[1].reduce(sum,0) + casesObj[4].reduce(sum,0));
// remember to add death in HCW when it's available
jq("#totalDeaths").html(casesObj[12].reduce(sum,0) + casesObj[10].reduce(sum,0));

            //////////////////////////////////////////////////////////

// For Epicurve
//cases deaths filter
casesdeathsF = casesData.filter((person) => {
  return ((person.status_d_a == '160432'));
})

result6 = casesdeathsF.reduce(function(r, a){
  
  var d = new Date(a.case_status_date);
  if (d.getDate() in r){
    r[d.getDate()]++
  }else{
    r[d.getDate()]= 1;
  }
  return r;
  }, Object.create(null))
  console.log(result6)

  var myData3 = Object.keys(result6).map(key => {
      return result6[key];
  });

//all cases

/*
result5 = casesData.reduce(function(r, a){
  
  var d = new Date(a.case_status_date);
  if (d.getDate() in r){
    r[d.getDate()]++
  }else{
    r[d.getDate()]= 1;
  }
  return r;
  }, Object.create(null))
*/

  result5 = casesData.reduce(function(r, a){
  
  var d = manuallyFormattedDate(a.case_status_date);
  if (d in r){
    r[d]++
  }else{
    r[d]= 1;
  }
  return r;
  }, Object.create(null))
  console.log(result5)
  
  
  var myData2 = Object.keys(result5).map(key => {
      return result5[key];
  });
  console.log(myData2);
  console.log(Object.keys(result5));
  

  casesDataSeries = myData2;
  deathDataSeries = myData3;
  catAxisX = Object.keys(result5);
  console.log("catAxisX");
  console.log(catAxisX);
  console.log("casesDataSeries");
  console.log(casesDataSeries);

    /////
   //var deathDataSeries = [0.8, 5.7];
   // var catAxisX = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
 
    


   var title = {
      text: 'COVID-19 Epicurve (showing trend in COVID-19 Cases and Deaths) '   
   };
   var subtitle = {
      text: 'Source: NMRS'
   };
   var xAxis = {
    title: {
         text: 'Date'
      },
      categories: catAxisX
   };
   var yAxis = {
      title: {
         text: 'Number of Cases'
      },
      plotLines: [{
         value: 0,
         width: 1,
         color: '#808080'
      }]
   };   

   var tooltip = {
    valueSuffix: ''
   }

   var legend = {
      layout: 'vertical',
      align: 'right',
      verticalAlign: 'middle',
      borderWidth: 0
   };

   var series =  [
      {
         name: 'Cases',
         data: casesDataSeries
      }, 
      {
         name: 'Deaths',
         data: deathDataSeries
      }
   ];
   var plotOptions = {
        line: {
            dataLabels: {
                enabled: true
            },
            enableMouseTracking: true
        }
    };

   var credits = {
    enabled: false
    }

   var json = {};

   json.title = title;
   json.subtitle = subtitle;
   json.xAxis = xAxis;
   json.yAxis = yAxis;
   json.tooltip = tooltip;
   json.legend = legend;
   json.series = series;
   json.plotOptions = plotOptions;
   json.credits = credits;
   

   jq('#containerz').highcharts(json);

  ////###############################
// For population pyramid
console.log("Pop pyramid");
var setBracket = 0;







pop_pyrad_f = casesData.filter((v) => {
  return (v.gender === 'F') || (v.gender === 'Female');
})


console.log(pop_pyrad_f);
pop_pyrad_m = casesData.filter((v) => {
  return (v.gender === 'M') || (v.gender === 'Male');
})

console.log(pop_pyrad_m);


//Female
pop_pyrad_f_reduce = pop_pyrad_f.reduce(function(r, a){
  
  if (setBracket != 0){
    //r[d.getDate()]++
    if(getAge(a.dob) < 1){r['<1']++;}
    else if(getAge(a.dob) >= 1 && getAge(a.dob) <= 4){r['1-4']++;}
    else if(getAge(a.dob) >= 5 && getAge(a.dob) <= 9){r['5-9']++;}
    else if(getAge(a.dob) >= 10 && getAge(a.dob) <= 14){r['10-14']++;}
    else if(getAge(a.dob) >= 15 && getAge(a.dob) <= 19){r['15-19']++;}
    else if(getAge(a.dob) >= 20 && getAge(a.dob) <= 24){r['20-24']++;}
    else if(getAge(a.dob) >= 25 && getAge(a.dob) <= 29){r['25-29']++;}
    else if(getAge(a.dob) >= 30 && getAge(a.dob) <= 34){r['30-34']++;}
    else if(getAge(a.dob) >= 35 && getAge(a.dob) <= 39){r['35-39']++;}
    else if(getAge(a.dob) >= 40 && getAge(a.dob) <= 44){r['40-44']++;}
    else if(getAge(a.dob) >= 45 && getAge(a.dob) <= 49){r['45-49']++;}
    else if(getAge(a.dob) >= 50 && getAge(a.dob) <= 54){r['50-54']++;}
    else if(getAge(a.dob) >= 55 && getAge(a.dob) <=59){r['55-59']++;}
    else if(getAge(a.dob) >= 60 && getAge(a.dob) <= 64){r['60-64']++;}
    else if(getAge(a.dob) >= 65){r['65 +']++;}
  }else{
    r['<1']= 0;
    r['1-4']= 0;
    r['5-9']= 0;
    r['10-14']= 0;
    r['15-19']= 0;
    r['20-24']= 0;
    r['25-29']= 0;
    r['30-34']= 0;
    r['35-39']= 0;
    r['40-44']= 0;
    r['45-49']= 0;
    r['50-54']= 0;
    r['55-59']= 0;
    r['60-64']= 0;
    r['65 +']= 0;
    setBracket = 1;
    if(getAge(a.dob) < 1){r['<1']++;}
    else if(getAge(a.dob) >= 1 && getAge(a.dob) <= 4){r['1-4']++;}
    else if(getAge(a.dob) >= 5 && getAge(a.dob) <= 9){r['5-9']++;}
    else if(getAge(a.dob) >= 10 && getAge(a.dob) <= 14){r['10-14']++;}
    else if(getAge(a.dob) >= 15 && getAge(a.dob) <= 19){r['15-19']++;}
    else if(getAge(a.dob) >= 20 && getAge(a.dob) <= 24){r['20-24']++;}
    else if(getAge(a.dob) >= 25 && getAge(a.dob) <= 29){r['25-29']++;}
    else if(getAge(a.dob) >= 30 && getAge(a.dob) <= 34){r['30-34']++;}
    else if(getAge(a.dob) >= 35 && getAge(a.dob) <= 39){r['35-39']++;}
    else if(getAge(a.dob) >= 40 && getAge(a.dob) <= 44){r['40-44']++;}
    else if(getAge(a.dob) >= 45 && getAge(a.dob) <= 49){r['45-49']++;}
    else if(getAge(a.dob) >= 50 && getAge(a.dob) <= 54){r['50-54']++;}
    else if(getAge(a.dob) >= 55 && getAge(a.dob) <=59){r['55-59']++;}
    else if(getAge(a.dob) >= 60 && getAge(a.dob) <= 64){r['60-64']++;}
    else if(getAge(a.dob) >= 65){r['65 +']++;}
  }
  return r;
  }, Object.create(null));
  console.log(pop_pyrad_f_reduce);
  
  
  var pop_pyrad_f_red_data = Object.keys(pop_pyrad_f_reduce).map(key => {
      return pop_pyrad_f_reduce[key];
  });
  var pop_pyrad_f_red_data_key = Object.keys(pop_pyrad_f_reduce);
  console.log(pop_pyrad_f_red_data);
  console.log(pop_pyrad_f_red_data_key);


//Male
setBracket = 0;
pop_pyrad_m_reduce = pop_pyrad_m.reduce(function(r, a){
  
  if (setBracket != 0){
    //r[d.getDate()]++
    if(getAge(a.dob) < 1){r['<1']--;}
    else if(getAge(a.dob) >= 1 && getAge(a.dob) <= 4){r['1-4']--;}
    else if(getAge(a.dob) >= 5 && getAge(a.dob) <= 9){r['5-9']--;}
    else if(getAge(a.dob) >= 10 && getAge(a.dob) <= 14){r['10-14']--;}
    else if(getAge(a.dob) >= 15 && getAge(a.dob) <= 19){r['15-19']--;}
    else if(getAge(a.dob) >= 20 && getAge(a.dob) <= 24){r['20-24']--;}
    else if(getAge(a.dob) >= 25 && getAge(a.dob) <= 29){r['25-29']--;}
    else if(getAge(a.dob) >= 30 && getAge(a.dob) <= 34){r['30-34']--;}
    else if(getAge(a.dob) >= 35 && getAge(a.dob) <= 39){r['35-39']--;}
    else if(getAge(a.dob) >= 40 && getAge(a.dob) <= 44){r['40-44']--;}
    else if(getAge(a.dob) >= 45 && getAge(a.dob) <= 49){r['45-49']--;}
    else if(getAge(a.dob) >= 50 && getAge(a.dob) <= 54){r['50-54']--;}
    else if(getAge(a.dob) >= 55 && getAge(a.dob) <=59){r['55-59']--;}
    else if(getAge(a.dob) >= 60 && getAge(a.dob) <= 64){r['60-64']--;}
    else if(getAge(a.dob) >= 65){r['65 +']--;}
  }else{
    r['<1']= 0;
    r['1-4']= 0;
    r['5-9']= 0;
    r['10-14']= 0;
    r['15-19']= 0;
    r['20-24']= 0;
    r['25-29']= 0;
    r['30-34']= 0;
    r['35-39']= 0;
    r['40-44']= 0;
    r['45-49']= 0;
    r['50-54']= 0;
    r['55-59']= 0;
    r['60-64']= 0;
    r['65 +']= 0;
    setBracket = 1;
    if(getAge(a.dob) < 1){r['<1']--;}
    else if(getAge(a.dob) >= 1 && getAge(a.dob) <= 4){r['1-4']--;}
    else if(getAge(a.dob) >= 5 && getAge(a.dob) <= 9){r['5-9']--;}
    else if(getAge(a.dob) >= 10 && getAge(a.dob) <= 14){r['10-14']--;}
    else if(getAge(a.dob) >= 15 && getAge(a.dob) <= 19){r['15-19']--;}
    else if(getAge(a.dob) >= 20 && getAge(a.dob) <= 24){r['20-24']--;}
    else if(getAge(a.dob) >= 25 && getAge(a.dob) <= 29){r['25-29']--;}
    else if(getAge(a.dob) >= 30 && getAge(a.dob) <= 34){r['30-34']--;}
    else if(getAge(a.dob) >= 35 && getAge(a.dob) <= 39){r['35-39']--;}
    else if(getAge(a.dob) >= 40 && getAge(a.dob) <= 44){r['40-44']--;}
    else if(getAge(a.dob) >= 45 && getAge(a.dob) <= 49){r['45-49']--;}
    else if(getAge(a.dob) >= 50 && getAge(a.dob) <= 54){r['50-54']--;}
    else if(getAge(a.dob) >= 55 && getAge(a.dob) <=59){r['55-59']--;}
    else if(getAge(a.dob) >= 60 && getAge(a.dob) <= 64){r['60-64']--;}
    else if(getAge(a.dob) >= 65){r['65 +']--;}
  }
  return r;
  }, Object.create(null));
  console.log(pop_pyrad_m_reduce);
  
  
  var pop_pyrad_m_red_data = Object.keys(pop_pyrad_m_reduce).map(key => {
      return pop_pyrad_m_reduce[key];
  });
  var pop_pyrad_m_red_data_key = Object.keys(pop_pyrad_m_reduce);
  console.log(pop_pyrad_m_red_data);
  console.log(pop_pyrad_m_red_data_key);

  /////



  // Age categories
var categories = pop_pyrad_f_red_data_key;
  
  Highcharts.chart('containerp', {
    chart: {
      type: 'bar'
    },
    title: {
      text: 'Distribution Pyramid (showing COVID 19 Cases)'
    },
    subtitle: {
      text: 'Age-Sex Distribution of cases. Source: NMRS'
    },
    accessibility: {
      point: {
        valueDescriptionFormat: '{index}. Age {xDescription}, {value}.'
      }
    },
    xAxis: [{
      categories: categories,
      reversed: true,
      labels: {
        step: 1
      },
      accessibility: {
        description: 'Age (male)'
      }
    }, { // mirror axis on right side
      opposite: true,
      reversed: false,
      categories: categories,
      linkedTo: 0,
      labels: {
        step: 1
      },
      accessibility: {
        description: 'Age (female)'
      }
    }],
    yAxis: {
      title: {
        text: 'Number of Cases'
      },
      labels: {
        formatter: function () {
          return Math.abs(this.value) + '';
        }
      },
      accessibility: {
        description: 'Population Figures',
        rangeDescription: 'Range: 0 to ...'
      }
    },

    plotOptions: {
      series: {
        stacking: 'normal'
            }
    },
  
    tooltip: {
      formatter: function () {
        return '<b>' + this.series.name + ', age ' + this.point.category + '</b><br/>' +
          'Figure: ' + Highcharts.numberFormat(Math.abs(this.point.y), 0) + '';
      }
    },
  
    series: [{
      name: 'Male',
      data: pop_pyrad_m_red_data
    }, {
      name: 'Female',
      data: pop_pyrad_f_red_data
    }],
    credits: {
    enabled: false
    }
  });


            /////////////////////////////////////////////////////////

                
            })




            
            
   } 
   
        
jq(".cv19details").click(function(e){
    //startDate = jq("#startDate").val();
    //endDate = jq("#endDate").val();
    var subSet = jq(this).attr("data-subset");
    var type = jq(this).attr("data-type");
    window.open("cv19details.page?type="+type+"&subset="+subSet+"&startDate="+startDate+"&endDate="+endDate, "_blank");
})        


</script>