package com.okali.arithmetic.acm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 参考思路网址:
 * <a href="https://blog.csdn.net/job_yi/article/details/9615471">网址</a>
 * @author OKali
 *
 */
public class Distance1007 {

    static class Point{
        double x;
        double y;
    }

    static Point[] points = new Point[100000];

    static Point[] list = new Point[100000];

    static int pointsIndex = 0;

    static int listIndex = 0;

    public static void main(String[] args) {
       d();
    }

    public static void d(){
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextLine()) {
            String n = cin.nextLine();
            if("0".equals(n)){
                return ;
            }

            pointsIndex = 0;
            int nn = Integer.parseInt(n);
            for(int i = 0;i<nn;i++){
                String line = cin.nextLine();
                StringTokenizer st = new StringTokenizer(line);

                Point point = points[pointsIndex++];
                if(point == null){
                    point = new Point();
                    point.x = Double.parseDouble(st.nextToken());
                    point.y = Double.parseDouble(st.nextToken());
                }else{
                    point.x = Double.parseDouble(st.nextToken());
                    point.y = Double.parseDouble(st.nextToken());
                }

                points[pointsIndex - 1] = point;
            }

            //排序
            Arrays.sort(points,0,pointsIndex,new Comparator<Point>() {
                public int compare(Point o1, Point o2) {

                    return Double.compare(o1.x,o2.x);
                }
            });

            double dis = getDistance(points,0,pointsIndex-1);
            double dd = dis / 2.0;
            DecimalFormat df = new DecimalFormat("0.00");
            System.out.println(df.format(dd));
        }
    }


    public static double getDistance(Point[] pointLlist,int l,int r) {
        if (l == r) {
            return 0.0;
        }

        if (l + 1 == r) {
            return getDistance(pointLlist[l], pointLlist[r]);
        }

        if (l + 2 == r) {
            double a = getDistance(pointLlist[l], pointLlist[l + 1]);
            double b = getDistance(pointLlist[l], pointLlist[l + 2]);
            double c = getDistance(pointLlist[l + 1], pointLlist[l + 2]);
            return getMin(a, getMin(b, c));
        }


        int mid = (l + r) >>> 1;
         double minDistance = getMin(getDistance(pointLlist, l, mid), getDistance(pointLlist, mid, r));
        final double doubleMinDistance = minDistance * minDistance;

        //X
        listIndex = 0;
        //中线左边部分
        for (int i = mid - 1; i >= l; i--) {
            double a = pointLlist[mid].x;
            double b = pointLlist[i].x;

            double c = a - b;
            if(c < 0){
                c = -1.0 * c;
            }

            if (c < minDistance) {
                list[listIndex++] = pointLlist[i];
            } else {
                break;
            }
        }

        //中线右边部分
        for (int i = mid + 1; i <= r; i++) {
            double a = pointLlist[mid].x;
            double b = pointLlist[i].x;

            double c = a - b;
            if(c < 0){
                c = -1.0 * c;
            }

            if (c < minDistance) {
                list[listIndex++] = pointLlist[i];
            } else {
                break;
            }
        }

        //排序
        Arrays.sort(list,0,listIndex,new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.y, o2.y);
            }
        });

        List<Point> ps = new ArrayList<Point>();
        mid = listIndex >>> 1;

        for (int i = 0; i < listIndex; i++) {

            double a = pointLlist[mid].y;
            double b = pointLlist[i].y;

            double c = a - b;
            if(c < 0){
                c = -1.0 * c;
            }
            if (c < minDistance) {
                ps.add(list[i]);
            }
        }

        for (int i = 0; i < ps.size(); i++) {
            for (int j = i + 1; j < ps.size(); j++) {
                double ax = ps.get(i).x;
                double bx = ps.get(j).x;
                double cx = ax - bx > 0 ? ax - bx : bx - ax;
                if(cx > minDistance){
                    continue;
                }

                double ay = ps.get(i).y;
                double by = ps.get(j).y;
                double cy = ay - by > 0 ? ay - by : by - ay;
                if(cy > minDistance){
                    continue;
                }


                double d = getDistance(ps.get(i), ps.get(j));
                if (minDistance > d) {
                    minDistance = d;
                }
            }
        }
        return minDistance;
    }

    public static double getDistance(Point a,Point b){
        return Math.sqrt((a.x  -  b.x)*(a.x - b.x) + (a.y -  b.y)*(a.y -  b.y));
    }


    public static double getMin(double a,double b){
        return a > b ? b : a;
    }
}

