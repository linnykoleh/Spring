package com.learning.linnyk;

import com.netflix.zuul.ZuulFilter;

public class ORZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // Logic fo the filter
        System.out.println("This request has passed through the custom Zuul filter...");
        return null;
    }
}
