


<figure class="highcharts-figure">
    <div id="container"></div>
    <p class="highcharts-description">
        Basic line chart showing trends in a dataset. This chart includes the
        <code>series-label</code> module, which adds a label to each line for
        enhanced readability.
    </p>
</figure>
<script>
var myChart = Highcharts.chart('container',{
    chart: {
    type:'line'
    },
    series: [{
    name: 'Data Set',
    data: [2,40,71,3,11,51,4,71,31]
    },
    {
    name: 'Data Set',
    data: [1,2,3,1,1,2]
    }]
    });    
</script>