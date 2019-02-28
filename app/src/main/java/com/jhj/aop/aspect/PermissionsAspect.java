package com.jhj.aop.aspect;

import android.app.Activity;
import android.widget.Toast;

import com.jhj.aop.annotation.Permissions;
import com.jhj.aop.permissions.PermissionsCheck;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PermissionsAspect {


    @Around("execution(@com.jhj.aop.annotation.Permissions void *(..)) && @annotation(permissions)")
    public void request(final ProceedingJoinPoint joinPoint, Permissions permissions) {
        if (permissions.value().length > 0) {
            final Activity activity = (Activity) joinPoint.getThis();
            PermissionsCheck.with(activity)
                    .requestPermissions(permissions.value())
                    .onPermissionsResult(new PermissionsCheck.OnPermissionsResultListener() {
                        @Override
                        public void onPermissionsResult(String[] deniedPermissions, String[] allPermissions) {
                            if (deniedPermissions.length == 0) {
                                try {
                                    joinPoint.proceed();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            } else {
                                for (String deniedPermission : deniedPermissions) {
                                    Toast.makeText(activity, deniedPermission + "请求失败", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                        }
                    });
        }
    }
}
