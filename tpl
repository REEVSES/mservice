[Unit]
Description=Kubernetes Kubelet
[Service]
#本地Node节点的kubelet工作目录，存储当前节点容器，pod等信息
WorkingDirectory=/var/lib/kubelet
ExecStart=/home/liuyao/bin/kubelet \
#对外服务的监听地址
--address=10.71.254.101 \
#访问集群方式的配置，如api-server地址等
--kubeconfig=/etc/kubernetes/kubelet.kubeconfig \
#声明cni网络插件
--network-plugin=cni \
#cni网络配置目录，kubelet会读取该目录下得网络配置
--cni-conf-dir=/etc/cni/net.d \
#指定 kubedns 的 Service IP(可以先分配，后续创建 kubedns 服务时指定该 IP)
#--cluster-domain 指定域名后缀，这两个参数同时指定后才会生效
--cluster-dns=10.68.0.2 \
...


