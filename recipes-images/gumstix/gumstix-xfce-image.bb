DESCRIPTION = "The Gumstix XFCE image.  This provides simple desktop \
environment using X11."
LICENSE = "MIT"

IMAGE_FEATURES += "x11-base"

require gumstix-console-image.bb

IMAGE_INSTALL += " \
    florence \
    gnome-bluetooth \
    chromium \
    man \
    man-pages \
    network-manager-applet \
    packagegroup-xfce-extended \
    packagegroup-xfce-multimedia \
    polkit-gnome \
    polkit-group-rule-network \
    polkit-group-rule-datetime \
"

IMAGE_INSTALL_remove_dragonboard-410c += " \
    chromium \
"

IMAGE_INSTALL_append_mx6 += " \
    xf86-video-imxfb-vivante \
    xserver-xorg-extension-viv-autohdmi \
    xserver-xorg-extension-glx \
"

# Network Manager manages WPA supplicant---we don't need an interface-specific
# systemd service in this case.
zap_wlan0_wpa() {
    rm -f ${IMAGE_ROOTFS}/etc/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
}

ROOTFS_POSTPROCESS_COMMAND =+ "zap_wlan0_wpa;"
