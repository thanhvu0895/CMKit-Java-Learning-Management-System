<t:form_with url="${UrlUtils.GRADE_CATEGORIES_PATH}"> 
 	<div class="form-group">
    <label for="grade_category_title">Title</label>
    <input class="form-control" type="text" name="grade_category[title]" id="grade_category_title" />
  </div>

  <div class="form-group">
    <label for="grade_category_Weight (percent)">Weight (percent)</label>
    <input class="form-control" step="any" type="number" name="grade_category[weight]" id="grade_category_weight" />
  </div>

  <div class="field">
    <input value="${course.id}" type="hidden" name="grade_category[course_id]" id="grade_category_course_id" />
  </div>

<!--   <div class="field">
    <input type="hidden" name="grade_category[klass_id]" id="grade_category_klass_id" />
  </div> -->
  
  <div class="actions">
    <input type="submit" name="commit" value="Create Grade category" class="btn btn-primary" data-disable-with="Create Grade category" />
  </div>
</t:form_with>
 

<%-- <%= form_with(model: grade_category, local: true) do |form| %>
  <% if grade_category.errors.any? %>
    <div id="error_explanation">
      <h2><%= pluralize(grade_category.errors.count, "error") %> prohibited this grade category from being saved:</h2>

      <ul>
      <% grade_category.errors.full_messages.each do |message| %>
        <li><%= message %></li>
      <% end %>
      </ul>
    </div>
  <% end %>

  <div class="form-group">
    <%= form.label :title %>
    <%= form.text_field :title , class: "form-control"%>
  </div>

  <div class="form-group">
    <%= form.label "Weight (percent)" %>
    <%= form.number_field :weight, class: "form-control", step: :any %>
  </div>

  <div class="field">
    <%= form.hidden_field :course_id, value: @grade_category.course_id%>
  </div>

  <div class="field">
    <%= form.hidden_field :klass_id, value: @grade_category.klass_id %>
  </div>
  
  <div class="actions">
    <%= form.submit class: "btn btn-primary" %>
  </div>
<% end %>
 --%>