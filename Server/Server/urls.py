"""Server URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.8/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import include, url
from django.contrib import admin

from dingdong.views import user, message

urlpatterns = [
    url(r'^admin/', include(admin.site.urls)),

    url(r'^user/login$', user.login),
    url(r'^user/logout$', user.logout),
    url(r'^user/register$', user.register),
    url(r'^user/update$', user.update),

    url(r'^user/(?P<username>[\w.@+-]+)$', user.get),
    url(r'^user/(?P<username>[\w.@+-]+)/friends$', user.list_friends),

    url(r'^user/(?P<source_username>[\w.@+-]+)/friends/add$', user.add_friend),
    url(r'^user/(?P<source_username>[\w.@+-]+)/messages/create$', message.create),
    url(r'^user/(?P<username1>[\w.@+-]+)/messages/(?P<username2>[\w.@+-]+)$', message.get),
]
