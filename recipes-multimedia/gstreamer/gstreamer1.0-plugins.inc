DESCRIPTION = "Plugins for GStreamer 1.x"
HOMEPAGE = "http://gstreamer.freedesktop.org/"
BUGTRACKER = "https://bugzilla.gnome.org/enter_bug.cgi?product=Gstreamer"
SECTION = "multimedia"
DEPENDS = "gstreamer1.0"

inherit autotools pkgconfig

FILESPATH =. "${FILE_DIRNAME}/gst-plugins:"

GSTREAMER_1_0_DEBUG ?= "--disable-debug"
EXTRA_OECONF = "--disable-valgrind ${GSTREAMER_1_0_DEBUG} --disable-examples "

acpaths = "-I ${S}/common/m4 -I ${S}/m4"

require gstreamer1.0-plugins-package.inc

PACKAGECONFIG[orc] = "--enable-orc,--disable-orc,orc"

PACKAGES_DYNAMIC = "${PN}-*"

python __anonymous () {
    # check if expected prefix is present
    prefix = "gstreamer1.0-"
    bpn = d.getVar("BPN", True)
    if not bpn.startswith(prefix):
        bb.fatal('Invalid GStreamer 1.0 plugin package name "%s" : must start with "%s"' % (bpn, prefix))

    # replaced prefix with "gst-", which is what is used for the tarball filenames
    tarball_basename = "gst-" + bpn[len(prefix):]
    pv = d.getVar("PV", True)

    d.setVar("SRC_URI", "http://gstreamer.freedesktop.org/src/%s/%s-%s.tar.xz" % (tarball_basename, tarball_basename, pv))
}

