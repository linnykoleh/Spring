package com.learning.linnyk;

public class ProfileBeanLive {

    private String profileValue;

    public ProfileBeanLive(String profileValue) {
        this.profileValue = profileValue;
    }

    public String getProfileValue() {
        return profileValue;
    }

    @Override
    public String toString() {
        return "ProfileBeanLive{" +
                "profileValue='" + profileValue + '\'' +
                '}';
    }
}
