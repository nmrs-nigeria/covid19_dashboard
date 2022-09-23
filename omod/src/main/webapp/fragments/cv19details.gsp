<% ui.decorateWith("appui", "standardEmrPage") %>
<%
def id = config.id
%>
<%=ui.resourceLinks()%>
<div class="container">
    
    <h2><%= title; %></h2>
    <br /><br />
<div class="table-responsive">
        <table class="table-bordered dataTable" width="100%">
            <thead>
                <th>S/N</th>
                <th>Pepfar ID</th>
                <th>Case ID</th>
                <th>Gender</th>
                <th>Date of Birth</th>
                <th>Date of Case Investigation</th>
                <th>ART Start Date</th>
                <!--<th>Age Range</th>-->
                
        </thead>
            <tbody>
                <% 
                 int sn = 1;
                for(int i=0; i<patients.size(); i++)
                {
                %>
                    
                    <tr>
                        <td><%= sn++ %></td>
                        <td><%= patients.get(i).getPepfar_id()%></td>
                        <td><%= patients.get(i).getCase_id()%></td>
                        <td><%= patients.get(i).getGender()%></td>
                        <td><%= patients.get(i).getDob()%></td>
                        <td><%= patients.get(i).getCase_status_date()%></td>
                        <td><%= patients.get(i).getArt_start_date()%></td>
                        
                    </tr>
        
                <%
                }   
                %>
                
            
            </tbody>
        </table>
        
    
    </div>


    
</div>

<script type="text/javascript">
    var jq = jQuery;
    
    jq(document).ready(function(e){

        
        jq(".dataTable").DataTable({
             pageLength: 100,
             "lengthMenu": [[50, 100, 250, 500, -1], [50, 100, 250, 500, "All"]],
             "columnDefs": [
                { "searchable": false, "targets": [0] }  // Disable search on first and last columns
              ],
              dom: 'Bfrtip',
                buttons: [
                    'copy', 
                   // 'csv', 
                    {
                        extend: 'csv',
                        title:'<%= title; %>',
                        //messageTop: '<%= title; %>'
                    },
                    {
                        extend: 'excel',
                        title:'<%= title; %>',
                        //messageTop: '<%= title; %>'
                    },
                    {
                        extend: 'pdf',
                        title:'<%= title; %>',
                        //messageTop: '<%= title; %>'
                    },
                    {
                        extend: 'print',
                        title:'<%= title; %>',
                        //messageTop: '<%= title; %>'
                    },
                    //'pdf', 
                    //'print'
                ]

                });
        
 
    });
</script>