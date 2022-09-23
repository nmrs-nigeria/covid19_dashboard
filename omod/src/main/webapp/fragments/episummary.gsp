<% 
int i=1;


def title = config.title

%>
<div class="container">
    <br/>
    <h2>EPI Summary of COVID-19 outbreak</h2>
   
    
 
    <table class="table dataTable">
        <thead>

             <tr>
                <th valign="center">S/N</th>
                <th  style="text-align:center" align="middle">Indicator</th>
                <th>Male</th>
               
                <th>Female</th>
                
                <th>Total</th>
                <th  style="text-align:center">Line List</th>

             </tr>
            
        </thead>
        <tbody>
            
       
            <% for(int j=1; j<=40; j++){%>
               <% if(j==39){ %>
               
               
                <tr>
                    <td><%= j%></td>
                    <td><div class="epitableInd<%= j%>"></div></td>
                    <td class="loadingView num4"  id="totalEnrolledM<%= j%>">-</td>
                    <td class="loadingView num4"  id="totalEnrolledF<%= j%>">-</td>
    
                    <td class="loadingView num4"  id="totalEnrolledTotal<%= j%>">-</td>
                    <td>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>
    
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a class="dropdown-item cv19details" data-subset="<%= j%>" data-type="1" href="javascript:void(0);"  >View List </a>
    
                                        </li>
                                       
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            
               <% def sub39 = ['a', 'b', 'c', 'd'] %>
               
               
               <% sub39.each{ %>
               
               
               
               
               <tr>
                    <td><% println j+it %></td>
                    <td><div class="epitableInd<%= j+it %>"></div></td>
                    <td class="loadingView num4"  id="totalEnrolledM<%= j+it %>">-</td>
                    <td class="loadingView num4"  id="totalEnrolledF<%= j+it %>">-</td>
    
                    <td class="loadingView num4"  id="totalEnrolledTotal<%= j+it %>">-</td>
                    <td>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>
    
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a class="dropdown-item cv19details" data-subset="<%= j%>" data-type="1" href="javascript:void(0);"  >View List </a>
    
                                        </li>
                                       
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            
            
            <% } %>
               
               <%}else{ %>
               
               
               <tr>
                <td><%= j%></td>
                <td><div class="epitableInd<%= j%>"></div></td>
                <td class="loadingView num4"  id="totalEnrolledM<%= j%>">-</td>
                <td class="loadingView num4"  id="totalEnrolledF<%= j%>">-</td>

                <td class="loadingView num4"  id="totalEnrolledTotal<%= j%>">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item cv19details" data-subset="<%= j%>" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            
            
               <% }%>
            <% }%>
            
            
                 
            <!--
            <tr>
                <td><%= i++%></td>
                <td><div class="epitableInd"></div></td>
                <td class="loadingView num4"  id="totalEnrolledM">-</td>
                <td class="loadingView num4"  id="totalEnrolledF">-</td>

                <td class="loadingView num4"  id="totalEnrolledTotal">-</td>
                <td>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="btn-group">
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"> <span data-bind="label">Action</span>&nbsp;<span class="caret"></span>

                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a class="dropdown-item otzDetails" data-subset="1" data-type="1" href="javascript:void(0);"  >View List </a>

                                    </li>
                                   
                                </ul>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            -->
            
            
            
        </tbody>
        
    </table>
</div>



<!-- Modal -->
<div class="modal fade" id="descriptionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width:70%; margin:0 auto;">
      <div class="modal-header">
        <h5 class="modal-title" id="modalTitle">Indicator Description</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="row">
              <div class="col-sm-12 col-md-4"><strong >title</strong></div>
              <div class="col-sm-12 col-md-8"><p id="otzIndicatorTitle"></p></div>
              <hr class="niceDarkHr"/>
          </div>
          
          <div class="row">
              <div class="col-sm-12 col-md-4"><strong >Description</strong></div>
              <div class="col-sm-12 col-md-8"><p id="otzIndicatorDescription"></p></div>
              <hr class="niceDarkHr"/>
          </div>
          <div class="row">
              <div class="col-sm-12 col-md-4"><strong >Source</strong></div>
              <div class="col-sm-12 col-md-8"><p id="otzIndicatorSource"></p></div>
              <hr class="niceDarkHr"/>
          </div>
           <div class="row">
              <div class="col-sm-12 col-md-4"><strong >Calculation</strong></div>
              <div class="col-sm-12 col-md-8"><p id="otzIndicatorCalculation"></p></div>
              <hr class="niceDarkHr"/>
          </div>
         
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

