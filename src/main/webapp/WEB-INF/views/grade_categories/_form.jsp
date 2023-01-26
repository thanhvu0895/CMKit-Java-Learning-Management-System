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