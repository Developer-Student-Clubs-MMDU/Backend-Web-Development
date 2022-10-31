from django.urls import path
from sampleapp import views
urlpatterns = [
    path('', views.index, name="home"),
]