package com.learning.linnyk.profile.beans;

public class ProfileBeanHisto {

    private String profileValue;

    public ProfileBeanHisto(String profileValue) {
        this.profileValue = profileValue;
    }

    public String getProfileValue() {
        return profileValue;
    }

    @Override
    public String toString() {
        return "ProfileBeanHisto{" +
                "profileValue='" + profileValue + '\'' +
                '}';
    }
}
