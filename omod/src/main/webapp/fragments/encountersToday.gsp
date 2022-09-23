<%
    def props = config.properties ?: ["type", "datetime", "location", "provider"]
%>
<table>
    <tr>
        <th>${ ui.message("Encounter.type") }</th>
        <th>${ ui.message("Encounter.datetime") }</th>
        <th>${ ui.message("Encounter.location") }</th>
    </tr>
    <% if (encounters) { %>
        <% encounters.each { %>
            <tr>
                <td>${ ui.format(it.encounterType) }</td>
                <td>${ ui.format(it.encounterDatetime) }</td>
                <td>${ ui.format(it.location) }</td>
            </tr>
        <% } %>
    <% } else { %>
        <tr>
            <td colspan="4">${ ui.message("general.none") }</td>
        </tr>
    <% } %>
</table>


<div id="chartdiv" style="width: 100%; height: 700px;"></div>
<!-- Tab links -->
<div class="tab">

    <button class="tablinks" onclick="openTab(event, 'dashboard')" id="defaultOpen">Reports Dashboard</button>

</div>

<script>

// Create the chart
var chart = am4core.create("chartdiv", am4plugins_sunburst.Sunburst);

// Add multi-level data
chart.data = [{
  name: "First",
  children: [
    { name: "A1", value: 100 },
    { name: "A2", value: 60 }
  ]
},
{
  name: "Second",
  children: [
    { name: "A1", value: 100 },
    { name: "A2", value: 60 }
  ]
},
{
  name: "Third",
  children: [
    { name: "A1", value: 100 },
    { name: "A2", value: 60 }
  ]
},
{
  name: "Fourth",
  children: [
    { name: "A1", value: 100 },
    { name: "A2", value: 60 }
  ]
},
{
  name: "Fifth",
  children: [
    { name: "A1", value: 100 },
    { name: "A2", value: 60 }
  ]
}
];

// Define data fields
chart.dataFields.value = "value";
chart.dataFields.name = "name";
chart.dataFields.children = "children";
chart.legend = new am4charts.Legend();
//chart.legend.valueLabels.template.text = "{value}";

chart.innerRadius = am4core.percent(10);


    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();

    function openTab(evt, tabName) {
        // Declare all variables
        var i, tabcontent, tablinks;

        // Get all elements with class="tabcontent" and hide them
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        // Get all elements with class="tablinks" and remove the class "active"
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }

        // Show the current tab, and add an "active" class to the button that opened the tab
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }

</script>